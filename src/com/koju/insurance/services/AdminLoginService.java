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
public class AdminLoginService implements AdminLoginInterface {
	@Autowired
	private Message message;
	@Autowired
	private Admin admin, tmpAdmin;
	private SessionFactory factory;
	private Session session;

	private void initValues() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	public AdminLoginService() {
		this.initValues();
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public Message login() {
		this.initValues();
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Admin WHERE email=? AND status=1");
			query.setString(0, this.admin.getEmail());
			this.tmpAdmin = (Admin) query.uniqueResult();
			if (this.tmpAdmin != null && encoder.matches(this.admin.getPassword(), this.tmpAdmin.getPassword())) {
				if(this.tmpAdmin.getInvalidCount()>=(byte)(5)) {
					this.message.setStatus(true);
					this.message.setMessage("Account Locked Due to multiple Invalid Access.!!! Please go through Forget password method to recover your account password.");
				}
				else {
					this.tmpAdmin.setInvalidCount((byte)(0));
					this.message.setStatus(false);
					this.message.setMessage("Welcome " + this.admin.getEmail());
				}
			} else if (this.tmpAdmin != null && this.tmpAdmin.getInvalidCount() < 5) {
				this.tmpAdmin.setInvalidCount((byte) (this.tmpAdmin.getInvalidCount() + 1));
				this.message.setStatus(true);
				this.message.setMessage("Invalid Password...!!!");
			} else {
				this.message.setStatus(true);
				this.message.setMessage("Invalid Email...!!!");
			}
			this.session.getTransaction().commit();
		} catch (Exception er) {
			this.message.setStatus(true);
			this.message.setMessage(er.getLocalizedMessage());
		}
		return this.message;
	}

}
