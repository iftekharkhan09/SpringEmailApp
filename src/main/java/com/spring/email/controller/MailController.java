package com.spring.email.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.spring.email.SimpleEmailService;
import com.spring.email.service.impl.MailSenderImpl;

public class MailController {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailSenderImpl.class);
		SimpleEmailService service = (SimpleEmailService) context.getBean("simpleEmailService");
		service.sendEmail();
	}
}