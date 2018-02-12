/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.mail;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MailServiceImpl is used to send a mail using mail.smtp.host and
 * mail.smtp.port to recipient with attachment
 * 
 * @author 999951
 * @return String (mail will be sent to recipient )
 */

@Service
@PropertySource("classpath:application.properties")
public class MailServiceImpl implements IMailService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public String mailWithAttachment(MailProperties mail) throws Exception {
		String from = env.getProperty("smtp.from");

		String to = mail.getMailto();

		String subject = mail.getSubject();

		String mes = mail.getMessage();
		String signature = mail.getSignature();

		String body = mes + "\n" + signature;
		logger.info(body);

		String filename = "mail_attachment\\Sampleattachment.txt";

		// Set smtp properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", env.getProperty("smtp.ge"));
		properties.put("mail.smtp.port", env.getProperty("smtp.port"));
		properties.put("mail.smtp.dsn.notify","SUCCESS,FAILURE ORCPT=rfc822;shamali.patil@ge.com");
		properties.put("mail.smtp.dsn.ret", "HDRS");
		Session session = Session.getDefaultInstance(properties, null);

		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			message.setSubject(subject);

			message.setSentDate(new Date());

			// Set the email body

			MimeBodyPart messagePart = new MimeBodyPart();

			messagePart.setText(body);

			// Set the email attachment file

			MimeBodyPart attachmentPart = new MimeBodyPart();

			FileDataSource fileDataSource = new FileDataSource(filename) {

				@Override
				public String getContentType() {

					return "application/octet-stream";

				}

			};

			attachmentPart.setDataHandler(new DataHandler(fileDataSource));

			attachmentPart.setFileName(fileDataSource.getName());

			// Add all parts of the email to Multipart object

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messagePart);

			multipart.addBodyPart(attachmentPart);

			message.setContent(multipart);

			// Send email

			Transport.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();

		}
		return "sent";
	}

	private MailData getDataForMail(MailProperties mail) {
		MailData generateMail = new MailData();

		int mail_ID = mail.getMailID();
		logger.info("mailID" + mail_ID);
		String body = "";

		switch (mail_ID) {
		case 0:
			generateMail.setSubject(mail.getSubject());
			logger.info(mail.getMessage());
			generateMail.setBody(mail.getMessage());
			generateMail.setBody(body);
			break;
		default:
			logger.info("Error in sending mail");

		}

		return generateMail;
	}

	@Override
	public JSONObject mailsender(MailProperties mail) throws JSONException {
		JSONObject msg = new JSONObject();
		MimeMessage message;
		MimeMessageHelper helper;
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtpmail.np.ge.com");
		sender.setPort(25);

		MailData generateMail = new MailData();
		generateMail = getDataForMail(mail);
		String from = env.getProperty("smtp.from");
		String subject = generateMail.getSubject();
		String body = generateMail.getBody();

		try {
			message = sender.createMimeMessage();
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			// helper.setCc("sharath.r1@ge.com");
			if (mail.getMailcc() != null) {
				String mcc = mail.getMailcc();
				String[] multicc = mcc.split(",", -1);
				helper.setCc(multicc);
			}

			// for (int i = 0; i < multicc.length; i++) {
			// message.setRecipient(Message.RecipientType.TO, new
			// InternetAddress(multicc[i]));
			// }

			String mto = mail.getMailto();
			String[] multirecp = mto.split(",", -1);

			// for (int i=0;i<multirecp.length;i++){
			// System.out.println(multirecp[i]);
			// }
			helper.setTo(multirecp);

			helper.setSubject(generateMail.getSubject());

			// Composing mail
			if (mail.getMailID() == 0) {
				String mailHtml = mail.getMessage();
				helper.setText(mailHtml, true);
			} else {
				// Auto generated mail
				String mailHtml = generateMail.getBody();
				helper.setText(mailHtml);
			}

			sender.send(message);
			msg.put("message", "Your message was sent successfully.");
			msg.put("status", "OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			msg.put("message", "Unable to send email.");
			msg.put("status", "OK");
			logger.error("Error in sending mail.");

		}
		return msg;

	}


}
