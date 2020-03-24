package com.koju.insurance.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.FamilyMembers;

@Service
public class FamilyDisplayService {
	@Autowired
	private Family family;

	private SessionFactory factory;
	private Session session;

	private void initFamilySession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Family.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	private void initFamilyMembersSession() {
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FamilyMembers.class)
				.buildSessionFactory();
		this.session = this.factory.getCurrentSession();
	}

	public FamilyDisplayService() {
		this.factory = null;
		this.session = null;
	}

	@Cacheable("allFamilies")
	public List<Family> getAllFamilies() {
		List<Family> families = new ArrayList<Family>();
		try {
			this.initFamilySession();
			this.session.beginTransaction();
			families = this.session.createQuery("FROM Family WHERE status=1").list();
			this.session.getTransaction().commit();
		} catch (Exception er) {
			families = null;
		}
		return families;
	}

	public Family getSingleFamily(String familyCode) {
		try {
			this.initFamilySession();
			this.session.beginTransaction();
			this.family = (Family) this.session.get(Family.class, Long.parseLong(familyCode));
			this.session.getTransaction().commit();
		} catch (Exception er) {
			this.family = null;
		}
		return this.family;
	}

	public List<FamilyMembers> getFamilyMembers(String familyCode) {
		List<FamilyMembers> members = new ArrayList<FamilyMembers>();
		try {
			this.initFamilyMembersSession();
			this.session.beginTransaction();
			Query query = this.session.createQuery("FROM FamilyMembers WHERE familyCode=? and status=1");
			query.setString(0, familyCode);
			members = query.list();
			this.session.getTransaction().commit();
		} catch (Exception er) {
			members = null;
		}
		return members;
	}

	@CacheEvict(value = "allFamilies", allEntries = true)
	public void removeFamilyCache() {

	}

	public List<Family> getSearchedFamilies(String familyCode, String familyHead, String appliedMonth, String regDate) {
		List<Family> families = new ArrayList<Family>();
		try {
			this.initFamilySession();
			this.session.beginTransaction();
			Query query;
			if (!familyCode.equals("-") && !familyHead.equals("-") && !appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and familyCode=? and appliedMonth=? and regDate=? and status=1");
				query.setString(0, familyHead);
				query.setLong(1, Long.parseLong(familyCode));
				query.setString(2, appliedMonth);
				query.setString(3, regDate);
				families = query.list();
			} else if (!familyCode.equals("-") && !familyHead.equals("-") && !appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and familyCode=? and appliedMonth=? and status=1");
				query.setString(0, familyHead);
				query.setLong(1, Long.parseLong(familyCode));
				query.setString(2, appliedMonth);
				families = query.list();
			} else if (!familyCode.equals("-") && !familyHead.equals("-") && appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and familyCode=? and regDate=? and status=1");
				query.setString(0, familyHead);
				query.setLong(1, Long.parseLong(familyCode));
				query.setString(2, regDate);
				families = query.list();

			} else if (!familyCode.equals("-") && !familyHead.equals("-") && appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and familyCode=? and status=1");
				query.setString(0, familyHead);
				query.setLong(1, Long.parseLong(familyCode));
				families = query.list();

			} else if (!familyCode.equals("-") && familyHead.equals("-") && !appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyCode=? and appliedMonth=? and regDate=? and status=1");
				query.setLong(0, Long.parseLong(familyCode));
				query.setString(1, appliedMonth);
				query.setString(2, regDate);
				families = query.list();

			} else if (!familyCode.equals("-") && familyHead.equals("-") && !appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyCode=? and appliedMonth=? and status=1");
				query.setLong(0, Long.parseLong(familyCode));
				query.setString(1, appliedMonth);
				families = query.list();

			} else if (!familyCode.equals("-") && familyHead.equals("-") && appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyCode=? and regDate=? and status=1");
				query.setLong(0, Long.parseLong(familyCode));
				query.setString(1, regDate);
				families = query.list();

			} else if (!familyCode.equals("-") && familyHead.equals("-") && appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyCode=? and status=1");
				query.setLong(0, Long.parseLong(familyCode));
				families = query.list();

			} else if (familyCode.equals("-") && !familyHead.equals("-") && !appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and appliedMonth=? and regDate=? and status=1");
				query.setString(0, familyHead);
				query.setString(1, appliedMonth);
				query.setString(2, regDate);
				families = query.list();

			} else if (familyCode.equals("-") && !familyHead.equals("-") && !appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and appliedMonth=? and status=1");
				query.setString(0, familyHead);
				query.setString(1, appliedMonth);
				families = query.list();

			} else if (familyCode.equals("-") && !familyHead.equals("-") && appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and regDate=? and status=1");
				query.setString(0, familyHead);
				query.setString(1, regDate);
				families = query.list();

			} else if (familyCode.equals("-") && !familyHead.equals("-") && appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE familyHead=? and status=1");
				query.setString(0, familyHead);
				families = query.list();

			} else if (familyCode.equals("-") && familyHead.equals("-") && !appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE and appliedMonth=? and regDate=? and status=1");
				query.setString(0, appliedMonth);
				query.setString(1, regDate);
				families = query.list();

			} else if (familyCode.equals("-") && familyHead.equals("-") && !appliedMonth.equals("-")
					&& regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE appliedMonth=? and status=1");
				query.setString(0, appliedMonth);
				families = query.list();

			} else if (familyCode.equals("-") && familyHead.equals("-") && appliedMonth.equals("-")
					&& !regDate.equals("-")) {
				query = this.session
						.createQuery("FROM Family WHERE regDate=? and status=1");
				query.setString(0, regDate);
				families = query.list();

			} else{
				families = new ArrayList<Family>();
				this.family.setFamilyHead("tttt");
				families.add(this.family);
			}
			if(families.toString().equals("[]")){
				families = new ArrayList<Family>();
				this.family.setFamilyHead("hhhh");;
				families.add(this.family);
			}
			this.session.getTransaction().commit();
		} catch (Exception er) {
			families = null;
		}
		return families;
	}
	@Cacheable("memberCodes")
	public List<FamilyMembers> getAllMemberCodes(){
		List<FamilyMembers> memberCodes = new ArrayList<FamilyMembers>();
		this.initFamilyMembersSession();
		try {
			this.session.beginTransaction();
			memberCodes = this.session.createQuery("FROM FamilyMembers WHERE status=1").list();
			this.session.getTransaction().commit();
		}catch(Exception er) {
			memberCodes =null;
		}
		return memberCodes;
	}
	
	@CacheEvict(value = "memberCodes", allEntries = true)
	public void removeMemberCodeCache() {
		
	}
}
