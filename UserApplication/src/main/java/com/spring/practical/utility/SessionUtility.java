package com.spring.practical.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.practical.model.SessionBean;
import com.spring.practical.model.User;
import com.spring.practical.service.RoleService;

@Component
public class SessionUtility {
	
	@Autowired
	private RoleService roleService;
	
	public void setSession(HttpSession session, HttpServletRequest request, User lst) {
		SessionBean sessionBean = new SessionBean();
		Long roleId = roleService.getRolesByUserId(lst.getId());
		sessionBean.setUserId(lst.getId());
		sessionBean.setUsername(lst.getUsername());
		sessionBean.setEmail(lst.getEmail());
		sessionBean.setUserType(roleId);
		session.setAttribute("UserSession", sessionBean);
		session.setMaxInactiveInterval(300);
	}
	
	
	public void setHeaders(HttpServletRequest request, HttpServletResponse response) {
		//response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    //response.setHeader("Pragma","no-cache");
	    //response.setDateHeader("Expires", 0);
	}


	/*
	 * public void setCookies(HttpServletResponse response) { String MY_APP =
	 * "My Own App"; boolean isHttpOnly = true; Cookie cookie = new
	 * Cookie("UserApp", MY_APP); cookie.setMaxAge(60 * 60); cookie.setSecure(true);
	 * cookie.setHttpOnly(isHttpOnly);
	 * 
	 * response.addCookie(cookie); }
	 */
}
