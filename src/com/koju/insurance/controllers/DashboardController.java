package com.koju.insurance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koju.insurance.beans.Family;

@Controller
public class DashboardController {
	@Autowired
	private Family family;
	
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboardPage(Model model) {
		model.addAttribute("family",this.family);
		return new ModelAndView("home","page","dashboard");
	}
}
