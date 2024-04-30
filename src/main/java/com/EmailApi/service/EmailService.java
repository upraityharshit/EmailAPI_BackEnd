package com.EmailApi.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
import javax.mail.*;


@Component
public class EmailService {

	public boolean sendEmail(String to, String subject, String message){
		
		boolean isSent = false;
		
		//from email
		String from = "Easycodingwithharshit@gmail.com";
		
		//variable for the gmail
		String host = "smtp.gmail.com";
		
		try {
			
			//get the system properties
			Properties properties = System.getProperties();
			System.out.println(properties);
			
			//setting important information to properties object
			
			//host set
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", true);
			properties.put("mail.smtp.starttls.enable", true);
			properties.put("mail.smtp.auth", true);
			
			//step 1: to get the session object...
		
			//Your gmail and Password will be used as Generated APP Password (normal gmail Password is not work)
			
			String username = "Easycodingwithharshit@gmail.com";
			String password = "uvpylxpzcsrukugw";
			
			Session session = Session.getInstance(properties, new Authenticator() {
	
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
	            
	        });
			
			session.setDebug(true);		
			
			//step 2: compose the message
			MimeMessage mess = new MimeMessage(session);
				
			//from message
			mess.setFrom(new InternetAddress(from));
			
			//adding recipient to message
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject of the message
			mess.setSubject(subject);
			
			//adding text of the message
			mess.setText(message);
			
			
			//step 3: send the message
			Transport.send(mess);
			
			System.out.println("Sent Successfully...");
			
			isSent = true;
			return isSent;
			
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		
		return isSent;
		
	}
}
