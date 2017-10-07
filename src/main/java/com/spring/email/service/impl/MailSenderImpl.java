package com.spring.email.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class MailSenderImpl implements com.spring.email.service.MailSender {
	@Override
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("");
		mailSender.setPort(25);
		return mailSender();
	}
}