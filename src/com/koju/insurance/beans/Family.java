package com.koju.insurance.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tbl_family")
public class Family implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="family_code")
	@Max(999999999)
	@Min(1)
	private long familyCode;
	
	@NotEmpty(message="Family head Required.")
	@Size(min=6,max=50,message="Name must contain character between 6 to 30")
	@Column(name="family_head")
	private String familyHead;
	
	@NotEmpty(message="Registered Date Required")
	@Column(name="reg_date")
	private String regDate;
	
	@NotEmpty(message="Expiry Date Required")
	@Column(name="expiry_date")
	private String expiryDate;
	
	@Digits(integer=10,fraction=2,message="Decimal value must be only 2 digits. Eg : 10.25")
	@Column(name="amount")
	@DecimalMin(value="0.0",inclusive=false)
	private BigDecimal amount;
	
	@Digits(integer=5,fraction=0,message="Must be digit")
	@Max(100)
	@Column(name="no_of_members")
	@Min(1)
	private int numOfMembers;
	
	@Column(name="contact_no")
	@NotEmpty(message="is required")
	@Size(min=10, max=10, message="invalid format")
	@Pattern(regexp="^[0-9]*$")
	private String contactNum;
	
	@NotEmpty(message="Applied Month Required")
	@Column(name="applied_month")
	private String appliedMonth;
	
	@Column(name="status")
	private boolean status;
	
	public Family() {
		this.familyCode = 0;
		this.familyHead = "";
		this.regDate = "";
		this.expiryDate = "";
		this.amount = new BigDecimal(0);
		this.numOfMembers = 0;
		this.contactNum = "";
		this.appliedMonth = "";
		this.status = true;
	}
	public Family(long familyCode, String familyHead, String regDate, String expiryDate,
			@Digits(integer = 10, fraction = 2, message = "Decimal value must be only 2 digits. Eg : 10.25") @DecimalMin(value = "0.0", inclusive = false) BigDecimal amount,
			int numOfMembers,
			@NotEmpty(message = "is required") @Size(min = 10, max = 10, message = "invalid format") @Pattern(regexp = "^[0-9]*$") String contactNum,
			String appliedMonth, boolean status) {
		this.familyCode = familyCode;
		this.familyHead = familyHead;
		this.regDate = regDate;
		this.expiryDate = expiryDate;
		this.amount = amount;
		this.numOfMembers = numOfMembers;
		this.contactNum = contactNum;
		this.appliedMonth = appliedMonth;
		this.status = status;
	}
	public Family(Family fm) {
		this.familyCode = fm.familyCode;
		this.familyHead = fm.familyHead;
		this.regDate = fm.regDate;
		this.expiryDate = fm.expiryDate;
		this.amount = fm.amount;
		this.numOfMembers = fm.numOfMembers;
		this.contactNum = fm.contactNum;
		this.appliedMonth = fm.appliedMonth;
		this.status = fm.status;
	}
	public long getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(long familyCode) {
		this.familyCode = familyCode;
	}
	public String getFamilyHead() {
		return familyHead;
	}
	public void setFamilyHead(String familyHead) {
		this.familyHead = familyHead;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getNumOfMembers() {
		return numOfMembers;
	}
	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getAppliedMonth() {
		return appliedMonth;
	}
	public void setAppliedMonth(String appliedMonth) {
		this.appliedMonth = appliedMonth;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Family [familyCode=" + familyCode + ", familyHead=" + familyHead + ", regDate=" + regDate
				+ ", expiryDate=" + expiryDate + ", amount=" + amount + ", numOfMembers=" + numOfMembers
				+ ", contactNum=" + contactNum + ", appliedMonth=" + appliedMonth + ", status=" + status + "]";
	}
}
