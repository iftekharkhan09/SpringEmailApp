package com.spring.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

@Component("simpleEmailService")
public class SimpleEmailService {
	private final String attachmentPath = "/Users/iftekharahmedkhan/logo.png";
	private final String sendTo = "iftekharkhan245@gmail.com";
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private TemplateEngine thymaleaf;

	@Autowired
	public SimpleEmailService(TemplateEngine thymaleaf) {
		this.thymaleaf = thymaleaf;
	}

	public SimpleEmailService() {
		// TODO Auto-generated constructor stub
	}

	public void sendEmailWithTemplate() throws MessagingException, IOException {
		Context context = new Context();
		context.setVariable("name", "harry");
		String emailMsg = thymaleaf.process("email-template", context);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setText(emailMsg, true);
		// FileSystemResource fileSystemResource = new
		// FileSystemResource(attachmentPath);
		String fileName = "logo.png";
		// ClassLoader classLoader = new
		// SimpleEmailService().getClass().getClassLoader();
		// File file = new File(classLoader.getResource(fileName).getFile());
		File file = new File("logo.png");
		if (!file.exists()) {
			System.out.println("File Does not exists!");
		}
		messageHelper.setTo(sendTo);
		messageHelper.setFrom("iftekharkhan629@gmail.com");
		messageHelper.setSubject("Test Mail");
		messageHelper.addInline("coupons", file);
		// mailSender.send(message);

		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials("", "");
		try {
			// credentialsProvider.
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/iftekharahmedkhan/.aws/credentials), and is in valid format.", e);
		}
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("us-west-2").build();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		message.writeTo(outputStream);
		RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
		SendRawEmailRequest rawMailRequest = new SendRawEmailRequest(rawMessage);
		client.sendRawEmail(rawMailRequest);
		System.out.println("Email Sent!");
	}
}
