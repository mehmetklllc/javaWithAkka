package com.turkishh.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * www.turkishh.com
 * 
 *   Mehmet KILIÇ
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
    	// Aktör oluşturabilmek için ActorSystem oluşturuyoruz 
    	// 'mail-sender-test' verdiğimiz bu isim her aktörün
    	// unique bir adresi olacak adresimiz  bu isim ile 
    	// başlayacaktır.
    	 ActorSystem system = ActorSystem.create("mail-sender-test");
    	 // Burada ilk olarak aktör yöneticimizi çalıştırıyoruz.
         ActorRef sendMail = system.actorOf(Props.create(ActorManager.class));
         
         
         MailObject mail1 = new MailObject("mail mesaj 1");
         sendMail.tell(mail1, ActorRef.noSender());
         System.out.println("> mail mesaj 1 gönderildi");
         
         
         
         MailObject mail2 = new MailObject("mail mesaj 2");
         sendMail.tell(mail2, ActorRef.noSender());
         System.out.println("> mail mesaj 2  gönderildi");
         
         
         
         MailObject mail3 = new MailObject("mail mesaj 3");
         sendMail.tell(mail3, ActorRef.noSender());
         System.out.println("> mail mesaj 3  gönderildi");
         
         
         
         MailObject mail4 = new MailObject("mail mesaj 4");
         sendMail.tell(mail4, ActorRef.noSender());
         System.out.println("> mail mesaj 4  gönderildi");
         
         
         MailObject mail5 = new MailObject("mail mesaj 5");
         sendMail.tell(mail5, ActorRef.noSender());
         System.out.println("> mail mesaj 5  gönderildi");
    }
}
