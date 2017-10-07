package com.spring.email.controller;

import javax.mail.MessagingException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.spring.email.SimpleEmailService;
import com.spring.email.service.impl.MailSenderService;

public class MailController {
	public static void main(String[] args) throws MessagingException {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailSenderService.class);
		SimpleEmailService service = (SimpleEmailService) context.getBean("simpleEmailService");
		// service.sendEmail();
		// service.sendEmailWithAttachment();
		 //service.sendEmailWithRichContent();
		service.sendEmailWithTemplate();
	}
}