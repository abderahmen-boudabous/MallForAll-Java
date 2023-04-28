/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodels;

import static java.lang.ProcessBuilder.Redirect.from;
import static java.util.Date.from;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;


/**
 *
 * @author user
 */
public class EmailUtils {
   public static void sendEmail(String to, String subject,String nom) throws Exception {
        
      String from = "skanderbey2040@gmail.com";
      String password = "$1$Nzf4lv5I$5iB/6KajEGqN4AI.ew9Gc1";
      String host = "smtp.gmail.com";  
      
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.socketFactory.port", "465");

      // Get default session object
     Session session;
       session = Session.getDefaultInstance(properties,
               new javax.mail.Authenticator() {
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication()  {
                       return new PasswordAuthentication(from, password);
                   }
               });

      // Create message
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      String htmlBody = "<html><body>" +
                         "<h1>Compte crée</h1>" +
                         "<p>Bonjour <strong>" + nom + "</strong> Votre compte a été crée avec succées</p>" +
                         "<p>Vous devez attendre la validation de l'adminstrateur,nous vous envoyons un email lorsque votre compte sera validé</p>" +
                         "</body></html>";
      
      message.setContent(htmlBody, "text/html");
      // Send message
      Transport.send(message);
      System.out.println("Message sent successfully!");
   }
   
   
   
   
    public static void sendVerificationCode(String to, String subject, String verificationCode) throws Exception {
        
      String from = "skanderbey2040@gmail.com";
      String password = "$1$Nzf4lv5I$5iB/6KajEGqN4AI.ew9Gc1";
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.socketFactory.port", "465");

      // Get default session object
      Session session = Session.getDefaultInstance(properties,
         new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(from, password);
            }
         });

      // Create message
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      String htmlBody = "<html><body>" +
                         "<h1>Code de vérification</h1>" +
                         "<p>Votre code de vérification est: <strong>" + verificationCode + "</strong></p>" +
                         "</body></html>";
      
      message.setContent(htmlBody, "text/html");
      // Send message
      Transport.send(message);
      System.out.println("Message sent successfully!");
   }
    
    
    
    
  
    public static void sendPassword(String to, String subject, String randomPassword) throws Exception {
        
      String from = "skanderbey2040@gmail.com";
      String password = "$1$Nzf4lv5I$5iB/6KajEGqN4AI.ew9Gc1";;
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.socketFactory.port", "465");

      // Get default session object
      Session session = Session.getDefaultInstance(properties,
         new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(from, password);
            }
         });

      // Create message
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      String htmlBody = "<html><body>" +
                         "<h1>Nouveau mot de passe</h1>" +
                         "<p>Votre nouveau mot de passe est: <strong>" + randomPassword + "</strong></p>" +
                         "<p>Vous devez changer votre mot de passe</p>" +
                         "</body></html>";
      
      message.setContent(htmlBody, "text/html");
      // Send message
      Transport.send(message);
      System.out.println("Message sent successfully!");
   }    
     
    
    
}