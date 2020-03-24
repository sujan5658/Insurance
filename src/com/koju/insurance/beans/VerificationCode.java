package com.koju.insurance.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_code_verification")
public class VerificationCode {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="code")
	private String code;
	@Column(name="session_id")
	private String sessionId;
	public VerificationCode() {
		this.id = 1;
		this.code = "";
		this.sessionId = "";
	}
	public VerificationCode(int id, String code, String sessionId) {
		this.id = id;
		this.code = code;
		this.sessionId = sessionId;
	}
	public VerificationCode(VerificationCode vc) {
		this.id = vc.id;
		this.code = vc.code;
		this.sessionId = vc.sessionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "VerificationCode [id=" + id + ", code=" + code + ", sessionId=" + sessionId + "]";
	}
}
