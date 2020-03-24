package com.koju.insurance.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.FamilyMembers;
import com.koju.insurance.beans.Message;
@Service
public class FamilyRegistrationService implements FamilyRegistrationInterface {
	@Autowired
	private Family family;
	@Autowired
	private FamilyMembers familyMember;
	@Autowired
	private Message message;
	@Autowired
	private FamilyDisplayService famDisServ;
	
	List<FamilyMembers> familyMembers;
	
	private SessionFactory factory;
	private Session session;
	
	private void initFamilySession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Family.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}
	private void initMemberSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(FamilyMembers.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}
	
	public FamilyRegistrationService() {
		this.factory = null;
		this.session = null;
		this.familyMembers = new ArrayList<FamilyMembers>();
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	public void setMembers(String familyMember) {
		String[] member = familyMember.split("%main%");
		String[] memberName = member[0].split("%sub%");
		String[] memberCode = member[1].split("%sub%");
		for(int i=0;i<memberName.length;i++) {
			this.familyMember = new FamilyMembers();
			this.familyMember.setMemberCode(Long.parseLong(memberCode[i]));
			this.familyMember.setMemberName(memberName[i]);
			this.familyMember.setFamilyCode(this.family.getFamilyCode());
			this.familyMember.setStatus(true);
			this.familyMembers.add(this.familyMember);
		}
	}
	@Override
	public Message registerFamily() throws SQLException {
		this.initFamilySession();
		try {
			this.session.beginTransaction();
			this.session.save(this.family);
			this.session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Family Registered Without Members");
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Family Register Error :" + er.getCause().getLocalizedMessage());
		}
		return this.message;
	}

	@Override
	public Message registerMembers() throws SQLException {
		this.initMemberSession();
		try {
			this.session.beginTransaction();
			for(FamilyMembers member:this.familyMembers) {
				this.session.save(member);
			}
			this.session.getTransaction().commit();
			this.famDisServ.removeFamilyCache();
			this.famDisServ.removeMemberCodeCache();
			this.message.setStatus(false);
			this.message.setMessage("Family Registration suceed.");
		}catch(Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Member Register Error :" + er.getCause().getLocalizedMessage());
		}
		return this.message;
	}

}
