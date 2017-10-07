package com.spring.email.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.spring.email.SimpleEmailService;
import com.spring.email.service.impl.MailSenderImpl;

public class MailController {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MailSenderImpl.class);
		//SimpleEmailService simpleEmailService = ctx.getBean(SimpleEmailService.class);
		//simpleEmailService.sendEmail();
	}
}
