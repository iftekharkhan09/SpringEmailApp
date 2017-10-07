package com.spring.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("simpleEmailService")
public class SimpleEmailService {
	@Autowired
	private org.springframework.mail.MailSender mailSender;

	public void sendEmail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("iftekharkhan245@gmail.com");
		simpleMailMessage.setSubject("Test Mail");
		simpleMailMessage.setText("This is a test mail");
		mailSender.send(simpleMailMessage);
	}
}