package com.spring.email.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.spring.email.SimpleEmailService;
import com.spring.email.service.impl.MailSenderService;

public class MailController {
	public static void main(String[] args) throws MessagingException, IOException {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailSenderService.class);
		SimpleEmailService service = (SimpleEmailService) context.getBean("simpleEmailService");
		// service.sendEmail();
		// service.sendEmailWithAttachment();
		 //service.sendEmailWithRichContent();
		service.sendEmailWithTemplate();
		
	}
}