package com.koju.insurance.services;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koju.insurance.beans.Admin;
import com.koju.insurance.beans.Email;
import com.koju.insurance.beans.Message;
import com.koju.insurance.beans.VerificationCode;
@Service
public class CodeVerificationService implements CodeVerificationInterface {
	@Autowired
	private Email email;
	@Autowired
	private Mailer mailer;
	@Autowired
	private Admin admin;
	@Autowired
	private Message message;
	@Autowired
	private VerificationCode verifyCode;
	
	int code;
	
	private SessionFactory factory;
	private Session session;
	
	private void initEmailSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(VerificationCode.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}
	private void initAdminSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Admin.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}
	public CodeVerificationService() {
		this.initEmailSession();
	}
	public void setReceiver(String email) {
		this.email.setReceiver(email);
	}
	@Override
	public Message validateEmail() {
		this.initAdminSession();
		try {
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Admin WHERE email=?");
			query.setString(0,this.email.getReceiver());
			this.admin = (Admin)query.uniqueResult();
			this.session.getTransaction().commit();
			if(this.admin!=null) {
				this.message.setStatus(false);
				this.message.setMessage("Valid Email");
			}
			else {
				this.message.setStatus(true);
				this.message.setMessage("Your Email is not in our System.");
			}
		}catch(Exception er) {
			this.message.setStatus(false);
			this.message.setMessage("Error : "+er.getLocalizedMessage());
		}
		return this.message;
	}
	@Override
	public Message uploadVerificationCode(HttpSession session) {
		try {
			this.initEmailSession();
			this.session.beginTransaction();
			this.verifyCode.setSessionId(session.getId());
			this.code = (int)(Math.random()*999999+100000);
			this.verifyCode.setCode(Integer.toString(code));
			this.session.save(this.verifyCode);
			this.session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("");
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage(er.getLocalizedMessage());
		}
		return this.message;
	}
	
	@Override
	public Message sendVerificationCode() {
		this.email.setSender("e42emart@gmail.com");
		this.email.setSenderPass("Dangerous@123");
		this.email.setHost("smtp.gmail.com");
		this.email.setPort("587");
		this.email.setMessage(code+" is your account recovery code. Ignore this mail if you haven't request recovery code.");
		this.email.setSubject("Your Account recovery Code.");
		this.mailer.setEmail(this.email);
		if(this.mailer.sendEmail()) {
			this.message.setStatus(false);
			this.message.setMessage("Account recovery code is sent to your Email.");
		}
		else {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error. Please try again later.");
		}
		return this.message;
	}

	@Override
	public Message verifyCode(String code,HttpSession session) {
		try {
			this.initEmailSession();
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM VerificationCode WHERE code=?");
			query.setString(0,code);
			this.verifyCode = (VerificationCode)query.uniqueResult();
			if(this.verifyCode!=null && this.verifyCode.getSessionId()==session.getId()) {
				this.message.setStatus(false);
				this.message.setMessage("Valid User");
				this.session.delete(this.verifyCode);
			}
			else if(this.verifyCode!=null) {
				this.message.setStatus(true);
				this.message.setMessage("Session Expired.!!! Try again.");
				this.session.delete(this.verifyCode);
			}
			else {
				query = this.session.createQuery("DELETE FROM VerificationCode");
				query.executeUpdate();
				this.message.setStatus(true);
				this.message.setMessage("Invalid Code. Try again.");
			}
			
			this.session.getTransaction().commit();
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage(er.getLocalizedMessage());
		}
		return this.message;
	}
	public Message resetPassword(String newPass) {
		this.initAdminSession();
		try {
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Admin WHERE email=?");
			query.setString(0,this.email.getReceiver());
			this.admin = (Admin)query.uniqueResult();
			this.admin.setPassword(new BCryptPasswordEncoder().encode(newPass));
			this.session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Password Recovered. Login to Proceed.");
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage(er.getLocalizedMessage());
		}
		return this.message;
	}
}
