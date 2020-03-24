package com.koju.insurance.services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koju.insurance.beans.Admin;
import com.koju.insurance.beans.Message;

@Service
public class ChangeCredentialsService {
	@Autowired 
	private Admin admin,tmpAdmin;
	@Autowired
	private Message message;
	
	private SessionFactory factory;
	private Session session;
	
	private void initAdminSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Admin.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}
	private ChangeCredentialsService() {
		this.factory = null;
		this.session = null;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Message updateAdmin(Admin admin) {
		this.initAdminSession();
		try {
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Admin WHERE email=? AND status=1");
			query.setString(0, this.admin.getEmail());
			this.tmpAdmin = (Admin)query.uniqueResult();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(this.tmpAdmin!=null && encoder.matches(this.admin.getPassword(),this.tmpAdmin.getPassword())) {
				this.tmpAdmin.setEmail(admin.getEmail());
				this.tmpAdmin.setPassword(encoder.encode(admin.getPassword()));
				this.tmpAdmin.setInvalidCount((byte)(0));
				this.message.setStatus(false);
				this.message.setMessage("Credentials Changed.!!!");
			}
			else {
				this.message.setStatus(true);
				this.message.setMessage("Invalid Old Pass.!!!!");
			}
			this.session.getTransaction().commit();
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage(er.getLocalizedMessage());
		}
		return this.message;
	}
}
