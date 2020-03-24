package com.koju.insurance.beans;

import org.springframework.stereotype.Component;

@Component
public class Email {
	private int email_id;
	private String sender;
	private String senderPass;
	private String receiver;
	private String subject;
	private String message;
	private String port;
	private String host;
	public Email() {
		this.email_id = 0;
		this.sender = "";
		this.senderPass = "";
		this.receiver = "";
		this.subject = "";
		this.message = "";
		this.port = "587";
		this.host = "smtp.gmail.com";
	}
	public Email(int email_id, String sender, String senderPass, String receiver, String subject, String message,
			String port, String host) {
		this.email_id = email_id;
		this.sender = sender;
		this.senderPass = senderPass;
		this.receiver = receiver;
		this.subject = subject;
		this.message = message;
		this.port = port;
		this.host = host;
	}
	public Email(Email em) {
		this.email_id = em.email_id;
		this.sender = em.sender;
		this.senderPass = em.senderPass;
		this.receiver = em.receiver;
		this.subject = em.subject;
		this.message = em.message;
		this.port = em.port;
		this.host = em.host;
	}
	public int getEmail_id() {
		return email_id;
	}
	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderPass() {
		return senderPass;
	}
	public void setSenderPass(String senderPass) {
		this.senderPass = senderPass;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	@Override
	public String toString() {
		return "Email [email_id=" + email_id + ", sender=" + sender + ", senderPass=" + senderPass + ", receiver="
				+ receiver + ", subject=" + subject + ", message=" + message + ", port=" + port + ", host=" + host
				+ ", getEmail_id()=" + getEmail_id() + ", getSender()=" + getSender() + ", getSenderPass()="
				+ getSenderPass() + ", getReceiver()=" + getReceiver() + ", getSubject()=" + getSubject()
				+ ", getMessage()=" + getMessage() + ", getPort()=" + getPort() + ", getHost()=" + getHost()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
