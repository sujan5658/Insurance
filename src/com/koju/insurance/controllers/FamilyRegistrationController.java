package com.koju.insurance.controllers;

import java.sql.SQLException;

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

import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.FamilyRegistrationService;

@Controller
public class FamilyRegistrationController {
	@Autowired
	private FamilyRegistrationService famRegSrv;
	@Autowired
	private Message message;
	@Autowired
	private Family family;

	@RequestMapping(value = "/registerFamily", method = RequestMethod.POST)
	public ModelAndView familyRegistrationProcess(@Valid @ModelAttribute("family") Family family, BindingResult result,
			@RequestParam("familyMembers") String members, Model model) {
		if (result.hasErrors()) {
			return new ModelAndView("home", "page", "dashboard");
		} else {
			if (members.equals("%main%")) {
				this.message.setStatus(true);
				this.message.setMessage("Family Members Required.!!!");
				model.addAttribute("message", this.message);
				model.addAttribute("family", family);
				return new ModelAndView("home", "page", "dashboard");
			} else {
				this.famRegSrv.setFamily(family);
				this.famRegSrv.setMembers(members);
				try {
					this.message = this.famRegSrv.registerFamily();
				} catch (SQLException er) {
					this.message.setStatus(true);
					model.addAttribute("family", family);
					return new ModelAndView("home", "page", "dashboard");
				}
				if (!this.message.isStatus()) {
					try {
						this.message = this.famRegSrv.registerMembers();
						model.addAttribute("message", this.message);
					} catch (SQLException er) {
						this.message.setStatus(true);
						model.addAttribute("family", family);
						return new ModelAndView("home", "page", "dashboard");
					}
				} else {
					model.addAttribute("message", this.message);
					model.addAttribute("family", family);
				}
				return new ModelAndView("home", "page", "dashboard");
			}
		}
	}
}
