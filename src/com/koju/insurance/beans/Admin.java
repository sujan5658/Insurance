package com.koju.insurance.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private int adminId;
	@Column(name="email")
	@NotEmpty(message="Email Required..!!!")
	@Email
	private String email;
	@Column(name="password")
	@NotEmpty(message="Password required..!!!")
	private String password;
	@Column(name="invalid_count")
	private byte invalidCount;
	public Admin() {
		this.adminId = 0;
		this.email = "";
		this.password = "";
		this.invalidCount=0;
	}
	public Admin(int adminId, String email, String password,byte invalidCount) {
		this.adminId = adminId;
		this.email = email;
		this.password = password;
		this.invalidCount = invalidCount;
	}
	public Admin(Admin adm) {
		this.adminId = adm.adminId;
		this.email = adm.email;
		this.password = adm.password;
		this.invalidCount = adm.invalidCount;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(byte invalidCount) {
		this.invalidCount = invalidCount;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", email=" + email + ", password=" + password + ", invalidCount="
				+ invalidCount + "]";
	}
}	
