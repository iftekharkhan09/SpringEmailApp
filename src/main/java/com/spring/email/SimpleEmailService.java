package com.spring.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component("simpleEmailService")
public class SimpleEmailService {
	private final String attachmentPath="/Users/iftekharahmedkhan/Documents/coupons.png";
	private final String sendTo="***************";
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private TemplateEngine thymaleaf;
	
	@Autowired
	public SimpleEmailService(TemplateEngine thymaleaf) {
		this.thymaleaf=thymaleaf;
	}
	//hotfix commit 1..

	public void sendEmail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(sendTo);
		simpleMailMessage.setSubject("Test Mail");
		simpleMailMessage.setText("This is a test mail");
		mailSender.send(simpleMailMessage);
	}
	//hotfix commit 2..
	
	public void sendEmailWithAttachment() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("iftekharkhan245@gmail.com");
		helper.setFrom("noreply@gmail.com");
		helper.setText("This is a test mail");
		helper.setSubject("Test Mail!");
		FileSystemResource fileSystemResource = new FileSystemResource(attachmentPath);
		helper.addAttachment("coupon.png", fileSystemResource);
		mailSender.send(message);
	}
	//hotfix commit 3..
	
	public void sendEmailWithRichContent() throws MessagingException {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
		messageHelper.setText("<html><body><h1>Hi !</h1><img src='cid:logo'></html></body>",true);
		FileSystemResource fileSystemResource=new FileSystemResource(attachmentPath);
		messageHelper.setTo("iftekharkhan245@gmail.com");
		messageHelper.setFrom("no-reply@gmail.com");
		messageHelper.setSubject("Test Mail");
		messageHelper.addInline("logo", fileSystemResource);
		mailSender.send(message);
	}	
	
	//hotfix commit 4..
	
	public void sendEmailWithTemplate() throws MessagingException {
		Context context=new Context();
		context.setVariable("name", "harry");
		String emailMsg=thymaleaf.process("email-template", context);
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
		messageHelper.setText(emailMsg,true);
		FileSystemResource fileSystemResource=new FileSystemResource(attachmentPath);
		messageHelper.setTo("*******************");
		messageHelper.setFrom("no-reply@gmail.com");
		messageHelper.setSubject("Test Mail");
		messageHelper.addInline("coupons", fileSystemResource);
		mailSender.send(message);
	}
}