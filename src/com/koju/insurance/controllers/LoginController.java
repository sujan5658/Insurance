package com.koju.insurance.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koju.insurance.beans.Admin;
import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.AdminLoginService;

@Controller
public class LoginController {
	@Autowired
	private Admin admin;
	@Autowired
	private Message message;
	@Autowired
	private AdminLoginService admLogServ;
	@Autowired
	private Family family;
	
	@RequestMapping(value="/")
	public ModelAndView LoginPage(Model model) {
		model.addAttribute("admin",this.admin);
		return new ModelAndView("adminLogin");
	}
	@RequestMapping(value="/login")
	public ModelAndView loginProcess(@Valid @ModelAttribute("admin") Admin admin,BindingResult result,Model model,HttpSession session) {
		if(result.hasErrors()) {
			return new ModelAndView("adminLogin");
		}
		else {
			this.admLogServ.setAdmin(admin);
			this.message = this.admLogServ.login();
			model.addAttribute("message",this.message);
			if(this.message.isStatus()) {
				model.addAttribute("admin",this.admin);
				return new ModelAndView("adminLogin");
			}
			else {
				session.setAttribute("sessionId",session.getId());
				session.setAttribute("userEmail",admin.getEmail());
				model.addAttribute("family",this.family);
				return new ModelAndView("home","page","dashboard");
			}
		}
	}
}
