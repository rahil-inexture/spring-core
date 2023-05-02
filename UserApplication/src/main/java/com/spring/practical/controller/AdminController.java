package com.spring.practical.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.practical.model.SessionBean;
import com.spring.practical.model.User;
import com.spring.practical.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/view-user-list", method=RequestMethod.GET)
	public String viewRegistrationPage(HttpServletRequest request, HttpSession  session, ModelMap modelMap) {
		SessionBean sessionBean =(SessionBean) session.getAttribute("UserSession");
		
		if(sessionBean != null) {
			List<User> userLst = userService.getAll();
			modelMap.addAttribute("userLst", userLst);
			return "userlists";
		}else {
			return "redirect:/login";
		}	
	}
}
