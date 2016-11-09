package com.turkishh.akka;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RandomRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

/**
 * www.turkishh.com
 * 
 * Mehmet KILIÇ
 */

// Aktör yöneticimizde aslında bir aktördür
// Sadece daha anlaşılır olması için ayağa kaldıracağımız 
// aktörleri buradan yönetelim istedim.

public class ActorManager extends UntypedActor {

	
	@Override
	public void preStart() {
		System.out.println("Aktör yöneticisi çalıştı.");

	}

	Router router;
	{
		List<Routee> routees = new ArrayList<Routee>();

		// Burada 3 tane aktör'ün çalışmasını istiyoruz.
		// işlemleri aktörlerimize gönderdiğimizde 3 tane aktör
		// iş yaptığını konsol çıktısında daha net göreceğiz.
		for (int i = 0; i < 3; i++) {
			ActorRef r = getContext().actorOf(Props.create(SendMail.class));
			getContext().watch(r);
			routees.add(new ActorRefRoutee(r));
		}
       // Router algoritmamızı burada veriyoruz.
	  
		
		router = new Router(new RandomRoutingLogic(), routees);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		
		// Burada ise işi atayacağımız aktöre göndereceğimiz
		// sınıfın istediğimiz türdenmi diye kontrol ediyoruz
		// eğer istediğimiz tipten bir mesaj değil ise aktöre iş atamıyoruz
		// Burada else kısmında aktörlerimizden birisi hata verip düşerse  tekrar 
		// yeni bir aktör create ediliyor.
		
		if (msg instanceof MailObject) {
			router.route(msg, getSender());
		} else if (msg instanceof Terminated) {
			router = router.removeRoutee(((Terminated) msg).actor());
			ActorRef r = getContext().actorOf(Props.create(SendMail.class));
			getContext().watch(r);
			router = router.addRoutee(new ActorRefRoutee(r));
		}

	}

}
