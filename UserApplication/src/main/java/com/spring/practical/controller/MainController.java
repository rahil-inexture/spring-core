package com.spring.practical.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.practical.model.Login;
import com.spring.practical.model.User;


@Controller
public class MainController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String viewLoginForm(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.addAttribute("login", new Login());
		return "login";
	}
	
	
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public String indexPage(Model model) {		
		return "index";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String viewRegistrationPage(ModelMap modelMap) {
		modelMap.addAttribute("userForm", new User());
		setPageData(modelMap);
		return "register";
	}
	
	public void setPageData(ModelMap model) {
		model.addAttribute("genderOptions", User.getGenderOptions());
	}
}