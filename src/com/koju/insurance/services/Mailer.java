package com.koju.insurance.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koju.insurance.beans.Email;

@Service
public class Mailer {
	@Autowired
	private Email email;

	private String userName;
	private String password;

	public Mailer() {
		this.email = null;
	}

	public void setEmail(Email email) {
		this.email = email;
		this.userName = email.getSender();
		this.password = email.getSenderPass();
	}

	public boolean sendEmail() {
		boolean result=false;
		try {
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", this.email.getHost());
			properties.put("mail.smtp.port", this.email.getPort());
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(userName));
			InternetAddress[] toAddresses = { new InternetAddress(this.email.getReceiver()) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(this.email.getSubject());
			msg.setSentDate(new Date());
			msg.setText(this.email.getMessage());

			Transport.send(msg);
			result =true;
		} catch (Exception er) {
			System.out.println("Mail Send Error : " + er.getLocalizedMessage());
		}
		return result;
	}
}
