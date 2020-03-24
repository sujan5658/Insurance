package com.koju.insurance.services;

import javax.servlet.http.HttpSession;

import com.koju.insurance.beans.Message;

public interface CodeVerificationInterface {
	public Message validateEmail();
	public Message uploadVerificationCode(HttpSession session);
	public Message sendVerificationCode();
	public Message verifyCode(String code,HttpSession session);
}
