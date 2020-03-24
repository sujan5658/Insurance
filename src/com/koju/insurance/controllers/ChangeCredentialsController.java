package com.koju.insurance.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koju.insurance.beans.Admin;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.ChangeCredentialsService;

@Controller
public class ChangeCredentialsController {
	@Autowired
	private Admin admin;
	@Autowired
	private Message message;
	@Autowired
	private ChangeCredentialsService chgCrdServ;
	
	@RequestMapping(value="/changeCredentials")
	public ModelAndView changeCredentialsPage(HttpSession session,Model model) {
		this.admin.setEmail(session.getAttribute("userEmail").toString());
		this.admin.setPassword("");
		model.addAttribute("admin",this.admin);
		return new ModelAndView("home","page","credentials");
	}
	
	@RequestMapping(value="/updateAdmin",method=RequestMethod.POST)
	public ModelAndView changeCredentialsProcess(HttpSession session,@Valid @ModelAttribute("admin") Admin admin,BindingResult result,@RequestParam("oldPass") String oldPass,Model model) {
		if(result.hasErrors()) {
			return new ModelAndView("home","page","credentials");
		}
		else {
			if(oldPass.equals("") || oldPass==null) {
				this.message.setStatus(true);
				this.message.setMessage("Old Password required..!!!");
				this.admin.setEmail(session.getAttribute("userEmail").toString());
				model.addAttribute("admin",this.admin);
				return new ModelAndView("home","page","credentials");
			}
			else {
				this.admin.setEmail(session.getAttribute("userEmail").toString());
				this.admin.setPassword(oldPass);
				this.chgCrdServ.setAdmin(this.admin);
				model.addAttribute("message",this.chgCrdServ.updateAdmin(admin));
				admin.setPassword("");
				model.addAttribute("admin",admin);
				return new ModelAndView("home","page","credentials");
			}
		}
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logoutProcess(HttpSession session,Model model) {
		session.invalidate();
		model.addAttribute("admin",new Admin());
		return new ModelAndView("adminLogin");
	}
}
