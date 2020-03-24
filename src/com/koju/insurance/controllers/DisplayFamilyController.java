package com.koju.insurance.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.koju.insurance.beans.Family;
import com.koju.insurance.beans.FamilyMembers;
import com.koju.insurance.beans.Message;
import com.koju.insurance.services.FamilyDisplayService;

@Controller
public class DisplayFamilyController {
	@Autowired
	private FamilyDisplayService famDisServ;
	@Autowired
	private Message message;
	@Autowired
	private Family family;
	
	@RequestMapping(value="/registeredFamily")
	public ModelAndView displayAllFamilies(Model model) {
		List<Family> families = new ArrayList<Family>();
		families = this.famDisServ.getAllFamilies();
		model.addAttribute("familiesList",families);
		model.addAttribute("families",families);
		return new ModelAndView("home","page","registeredFamily");
	}
	
	@RequestMapping(value="/singleFamily",method=RequestMethod.GET)
	public ModelAndView displaySingleFamiliy(Model model,@RequestParam("familyCode") String familyCode) {
		List<FamilyMembers> members = new ArrayList<FamilyMembers>();
		this.family = this.famDisServ.getSingleFamily(familyCode);
		if(this.family!=null) {
			members = this.famDisServ.getFamilyMembers(familyCode);
			if(members!=null) {
				model.addAttribute("family",this.family);
				model.addAttribute("members",members);
				return new ModelAndView("home","page","singleFamily");
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
	@RequestMapping(value="/searchFamily",method=RequestMethod.POST)
	public ModelAndView displaySearchedFamilies(Model model,@RequestParam("familyCode") String familyCode,@RequestParam("familyHead") String familyHead
			,@RequestParam("appliedMonth") String appliedMonth,@RequestParam("regDate") String regDate) {
		List<Family> families = new ArrayList<Family>();
		families = this.famDisServ.getSearchedFamilies(familyCode, familyHead, appliedMonth, regDate);
		if(families!=null) {
			if(families.get(0).getFamilyCode()!=0) {
				model.addAttribute("families",families);
			}
			else if(families.get(0).getFamilyHead().equals("tttt")) {
				this.message.setStatus(true);
				this.message.setMessage("Please Select Correct Search Criteria.");
				model.addAttribute("message",this.message);
				model.addAttribute("families",this.famDisServ.getAllFamilies());
			}
			else if(families.get(0).getFamilyHead().equals("hhhh")){
				this.message.setStatus(true);
				this.message.setMessage("Search Not Found..!!!");
				model.addAttribute("message",this.message);
				model.addAttribute("families",this.famDisServ.getAllFamilies());
			}
			
		}
		else {
			this.message.setStatus(true);
			this.message.setMessage("Internal Error...!!!.");
			model.addAttribute("message",this.message);
			model.addAttribute("families",this.famDisServ.getAllFamilies());
		}
		List<Family> fam = new ArrayList<Family>();
		fam = this.famDisServ.getAllFamilies();
		model.addAttribute("familiesList",fam);
		return new ModelAndView("home","page","registeredFamily");
	}
}
