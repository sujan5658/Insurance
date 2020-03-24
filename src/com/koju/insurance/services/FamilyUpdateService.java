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
public class FamilyUpdateService {
	@Autowired
	private Family family, tmpFamily;
	@Autowired
	private FamilyMembers familyMember;
	List<FamilyMembers> familyMembers;
	@Autowired
	private Message message;
	@Autowired
	private FamilyDisplayService famDisServ;

	private SessionFactory factory;
	private Session session;

	private void initFamilySession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Family.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	private void initMemberSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FamilyMembers.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	public FamilyUpdateService() {
		this.factory = null;
		this.session = null;
		this.familyMembers = new ArrayList<FamilyMembers>();
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public void setMembers(String familyMember, String memberStatus) {
		this.familyMembers = new ArrayList<FamilyMembers>();
		String[] member = familyMember.split("%main%");
		String[] memberName = member[0].split("%sub%");
		String[] memberCode = member[1].split("%sub%");
		String[] status = memberStatus.split("%status%");
		for (int i = 0; i < memberName.length; i++) {
			this.familyMember = new FamilyMembers();
			this.familyMember.setMemberCode(Long.parseLong(memberCode[i]));
			this.familyMember.setMemberName(memberName[i]);
			this.familyMember.setFamilyCode(this.family.getFamilyCode());
			for(int j =0;j<status.length;j++) {
				if(memberCode[i].equals(status[j])) {
					this.familyMember.setStatus(false);
				}
			}
			this.familyMembers.add(this.familyMember);
		}
	}

	public Message updateFamily() throws SQLException {
		this.initFamilySession();
		try {
			this.session.beginTransaction();
			this.session.saveOrUpdate(this.family);
			this.session.getTransaction().commit();
			this.message.setStatus(false);
			this.message.setMessage("Family Registered Without Members");
		} catch (Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Family Upadte Error :" + er.getCause().getLocalizedMessage());
		}
		return this.message;
	}

	public Message updateMembers() throws SQLException {
		this.initMemberSession();
		try {
			this.session.beginTransaction();
			for (FamilyMembers member : this.familyMembers) {
				this.session.merge(member);
				//this.session.saveOrUpdate(member);
			}
			this.session.getTransaction().commit();
			this.famDisServ.removeMemberCodeCache();
			this.famDisServ.removeFamilyCache();
			this.message.setStatus(false);
			this.message.setMessage("Family Renew suceed.");
		} catch (Exception er) {
			this.message.setStatus(true);
			this.message.setMessage("Member Register Error :"+er.getMessage());
		}
		return this.message;
	}
}
