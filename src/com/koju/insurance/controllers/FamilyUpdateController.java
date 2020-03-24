package com.koju.insurance.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.koju.insurance.beans.FamilyMembers;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.FamilyDisplayService;
import com.koju.insurance.services.FamilyUpdateService;

@Controller
public class FamilyUpdateController {
	@Autowired
	private FamilyDisplayService famDisServ;
	@Autowired
	private Message message;
	@Autowired
	private Family family;
	@Autowired
	private FamilyUpdateService famUpServ;
	
	@RequestMapping(value="/updateFamily",method=RequestMethod.GET)
	public ModelAndView updateFamilyPage(@RequestParam("familyCode") String familyCode,Model model) {
		List<FamilyMembers> members = new ArrayList<FamilyMembers>();
		this.family = this.famDisServ.getSingleFamily(familyCode);
		if(this.family!=null) {
			members = this.famDisServ.getFamilyMembers(familyCode);
			if(members!=null) {
				model.addAttribute("family",this.family);
				model.addAttribute("members",members);
				List<FamilyMembers> memberCodes = new ArrayList<FamilyMembers>();
				memberCodes = this.famDisServ.getAllMemberCodes();
				model.addAttribute("memberCodes",memberCodes);
				return new ModelAndView("home","page","updateFamily");
			}
			else {
				this.message.setStatus(true);
				this.message.setMessage("Internal Family Error..!!");
				model.addAttribute("families",this.famDisServ.getAllFamilies());
				return new ModelAndView("home","page","registeredFamily");
			}
		}
		else {
			this.message.setStatus(true);
			this.message.setMessage("Internal Member Error.!!!");
			model.addAttribute("families",this.famDisServ.getAllFamilies());
			return new ModelAndView("home","page","registeredFamily");
		}
	}
	
	@RequestMapping(value = "/renewFamily", method = RequestMethod.POST)
	public ModelAndView familyRegistrationProcess(@Valid @ModelAttribute("family") Family family, BindingResult result,
			@RequestParam("familyMembers") String members,@RequestParam("memberStatus") String memberStatus, Model model) {
		if (result.hasErrors()) {
			return new ModelAndView("home", "page", "updateFamily");
		} else {
			List<FamilyMembers> mem = new ArrayList<FamilyMembers>();
			mem = this.famDisServ.getFamilyMembers(Long.toString(family.getFamilyCode()));
			if (members.equals("%main%")) {
				this.message.setStatus(true);
				this.message.setMessage("Family Members Required.!!!");
				model.addAttribute("message", this.message);
				model.addAttribute("family", family);
				model.addAttribute("members",mem);
				return new ModelAndView("home", "page", "updateFamily");
			} else {
				this.famUpServ.setFamily(family);
				this.famUpServ.setMembers(members,memberStatus);
				try {
					this.message = this.famUpServ.updateFamily();
				} catch (SQLException er) {
					this.message.setStatus(true);
					model.addAttribute("family", family);
					model.addAttribute("members",mem);
					return new ModelAndView("home", "page", "updateFamily");
				}
				if (!this.message.isStatus()) {
					try {
						this.message = this.famUpServ.updateMembers();
						mem = this.famDisServ.getFamilyMembers(Long.toString(family.getFamilyCode()));
						model.addAttribute("message", this.message);
						List<FamilyMembers> memberCodes = new ArrayList<FamilyMembers>();
						memberCodes = this.famDisServ.getAllMemberCodes();
						model.addAttribute("memberCodes",memberCodes);
					} catch (SQLException er) {
						this.message.setStatus(true);
						model.addAttribute("family", family);
						model.addAttribute("members",mem);
						return new ModelAndView("home", "page", "updateFamily");
					}
				} else {
					model.addAttribute("message", this.message);
					model.addAttribute("family", family);
				}
				model.addAttribute("members",mem);
				return new ModelAndView("home", "page", "updateFamily");
			}
		}
	}
}
