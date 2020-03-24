package com.koju.insurance.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_family_members")
public class FamilyMembers implements Serializable {
	@Id
	@Column(name="member_code")
	private long memberCode;

	@Column(name="family_code")
	private long familyCode;
	
	@Column(name="member_name")
	private String memberName;
	
	@Column(name="status")
	private boolean status;
	
	public FamilyMembers() {
		this.memberCode = 0;
		this.familyCode = 0;
		this.memberName = "";
		this.status = true;
	}
	public FamilyMembers(long memberCode, long familyCode, String memberName, boolean status) {
		this.memberCode = memberCode;
		this.familyCode = familyCode;
		this.memberName = memberName;
		this.status = status;
	}
	public FamilyMembers(FamilyMembers fmem) {
		this.memberCode = fmem.memberCode;
		this.familyCode = fmem.familyCode;
		this.memberName = fmem.memberName;
		this.status = fmem.status;
	}
	public long getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(long memberCode) {
		this.memberCode = memberCode;
	}
	public long getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(long familyCode) {
		this.familyCode = familyCode;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "FamilyMembers [memberCode=" + memberCode + ", familyCode=" + familyCode + ", memberName=" + memberName
				+ ", status=" + status + "]";
	}
}
