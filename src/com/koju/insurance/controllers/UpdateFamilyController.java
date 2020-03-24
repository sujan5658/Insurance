package com.koju.insurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdateFamilyController {
	
	@RequestMapping(value="/updateFamily")
	public ModelAndView updateFamilyProcess() {
		return new ModelAndView("home","page","updateFamily");
	}
}
