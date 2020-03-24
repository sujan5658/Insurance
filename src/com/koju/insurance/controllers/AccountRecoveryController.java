package com.koju.insurance.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koju.insurance.beans.Admin;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.CodeVerificationService;

@Controller
public class AccountRecoveryController {
	@Autowired
	private CodeVerificationService codeVserv;
	@Autowired
	private Message message;
	@Autowired
	private Admin admin;
	
	@RequestMapping(value="/sendVerification",method=RequestMethod.POST)
	public ModelAndView sendVerificationCode(HttpSession session,@RequestParam("email") String email,Model model) {
		this.codeVserv.setReceiver(email);
		this.message = this.codeVserv.validateEmail();
		if(!this.message.isStatus()){
			this.message = this.codeVserv.uploadVerificationCode(session);
			if(!this.message.isStatus()) {
				this.message = this.codeVserv.sendVerificationCode();
				if(!this.message.isStatus()) {
					model.addAttribute("message",this.message);
					return new ModelAndView("recoveryCode");
				}
				else {
					model.addAttribute("message",this.message);
				}
			}
			else {
				model.addAttribute("message",this.message);
			}
		}
		else {
			model.addAttribute("message",this.message);
		}
		model.addAttribute("admin",this.admin);
		return new ModelAndView("adminLogin");
	}
	@RequestMapping(value="/checkRecoveryCode",method=RequestMethod.POST)
	public ModelAndView checkRecoveryCodeProcess(Model model,HttpSession session,@RequestParam("code") String code) {
		this.message = this.codeVserv.verifyCode(code, session);
		if(!this.message.isStatus()) {
			return new ModelAndView("resetPass");
		}else {
			model.addAttribute("message",this.message);
			model.addAttribute("admin",this.admin);
			return new ModelAndView("adminLogin");
		}
	}
	@RequestMapping(value="/setNewPass",method=RequestMethod.POST)
	public ModelAndView setNewPassProcess(Model model,@RequestParam("newPass") String newPass) {
		model.addAttribute("message",this.codeVserv.resetPassword(newPass));
		model.addAttribute("admin",this.admin);
		return new ModelAndView("adminLogin");
	}
}
