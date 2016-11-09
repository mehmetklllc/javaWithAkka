package com.turkishh.akka;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import akka.actor.UntypedActor;
/**
 * www.turkishh.com
 * 
 *   Mehmet KILIÇ
 */

  // Bir sınıfın aktör olabilmesi için
  // UntypedActor isimli sınıftan türemesi lazım
  // Bu sınıftan miras aldığımızda onReceive() methodunu
  // ezmemizi zorunlu kılıyor.
 public class SendMail extends UntypedActor {
	 private static int i=1;
	 
	//Bir aktör çağırıldığında
	// ilk çalışan methodumuz
	// bu methodu kaç tane aktör ayağa kaldırdığımızı 
	// daha net bir biçimde görebilmemiz için
	// oluşturdum. 
	@Override
	public void preStart() {
		System.out.println(i+". Mail Gönderici Başlatıldı.");
		i++;
	}
	
	
   // Bir aktör çağırıldığında bu methoda her defasında düşer 
   // preStart() metodu aktör oluşturulduğunda yani yeni create edildiğinde çalışır.
   // onReceive() metodunda  ise farketmez ayakta olan veya yeni create edilmiş 
   // aktör her defasında bu metodu çağırır.
   // 
	
	@Override
	public void onReceive(Object msg) throws Exception {
		
		
	    if(msg instanceof MailObject) {
        	MailObject o = (MailObject) msg;
            System.out.println("<<< START : " + o.getMessage()+ " isimli işlem başladı " + " aktör adresi : " +getSelf().path());
            Random r=new Random(); 
            int a=r.nextInt(5);
            TimeUnit.SECONDS.sleep(a);
            System.out.println("<<< END : " + o.getMessage()+ " isimli işlem bitirdi. " + " aktör adresi : " +getSelf().path());
        }
		
	}

	
}
