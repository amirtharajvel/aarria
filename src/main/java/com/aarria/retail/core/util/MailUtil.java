package com.aarria.retail.core.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

	protected static final String USERNAME = "fallsbuy@gmail.com";
	protected static final String PASSWORD = "harekrishna-1987";

	public static void sendEmail(String mailTo, String mailSubject, String mailText) {

		new Thread(() -> {
			Properties config = createConfiguration();

			Session session = Session.getInstance(config, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MailUtil.USERNAME, MailUtil.PASSWORD);
				}
			});

			Message message = new MimeMessage(session);
			try {
				message.setSentDate(new Date());
				message.setFrom(new InternetAddress(USERNAME));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
				message.setSubject(mailSubject);
				// message.setText(mailText);
				message.setContent(mailText, "text/html");

				Transport.send(message);

			} catch (MessagingException e) {
				//throw new RuntimeException("Unable to send email from gmail ", e);
			}
		}).start();
	}

	private static Properties createConfiguration() {
		return new Properties() {
			private static final long serialVersionUID = 1L;

			{
				put("mail.smtp.host", "smtp.gmail.com");
				put("mail.smtp.auth", "true");
				put("mail.smtp.port", "465");
				put("mail.smtp.socketFactory.port", "465");
				put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			}
		};
	}

	public static void sendWithAttachment(String mailTo, String mailSubject, String mailText,
			String attachmentFileLocation, String attachmentName) {
		final String username = USERNAME;
		final String password = PASSWORD;

		Properties props = createConfiguration();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			message.setSubject(mailSubject);
			message.setText(mailText);

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String file = attachmentFileLocation;
			String fileName = attachmentName;
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public static void sendEmailWithAttachment(String mailTo, String mailSubject, String mailText) {
		String to = mailTo;
		final String user = USERNAME; // change accordingly
		final String password = PASSWORD;

		// 1) get the session object
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "mail.javatpoint.com");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Message Aleart");

			// 3) create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			// 4) create new MimeBodyPart object and set DataHandler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "SendAttachment.java";// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);

			System.out.println("message sent....");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}

	}
}