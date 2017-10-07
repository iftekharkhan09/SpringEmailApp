package com.spring.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("simpleEmailService")
public class SimpleEmailService {
	private final String attachmentpath="/Users/iftekharahmedkhan/Documents/coupons.png";
	private final String sendTo="***************";
	@Autowired
	private JavaMailSenderImpl mailSender;

	public void sendEmail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(sendTo);
		simpleMailMessage.setSubject("Test Mail");
		simpleMailMessage.setText("This is a test mail");
		mailSender.send(simpleMailMessage);
	}

	public void sendEmailWithAttachment() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo("iftekharkhan245@gmail.com");
		helper.setText("This is a test mail");
		helper.setSubject("Test Mail!");
		FileSystemResource fileSystemResource = new FileSystemResource(attachmentpath);
		helper.addAttachment("coupon.png", fileSystemResource);
		mailSender.send(message);
	}
}