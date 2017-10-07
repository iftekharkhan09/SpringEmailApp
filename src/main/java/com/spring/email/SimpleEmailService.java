package com.spring.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

public class SimpleEmailService {
	@Autowired
	private org.springframework.mail.MailSender mailSender;
	
	public void MailMessage() {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo("");
		simpleMailMessage.setCc("");
		simpleMailMessage.setSubject("This is a test mail");
		mailSender.send(simpleMailMessage);
	}
}