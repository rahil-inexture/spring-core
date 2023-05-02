package com.spring.practical.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.practical.model.Address;
import com.spring.practical.model.Login;
import com.spring.practical.model.SessionBean;
import com.spring.practical.model.User;
import com.spring.practical.service.AddressService;
import com.spring.practical.service.RoleService;
import com.spring.practical.service.UserService;
import com.spring.practical.utility.CommonUtility;
import com.spring.practical.utility.PasswordEncDec;
import com.spring.practical.utility.SessionUtility;
import com.spring.practical.validator.LoginValidator;
import com.spring.practical.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private LoginValidator loginValidator;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private SessionUtility SessionUtility;
	@Autowired
	private AddressService addressService;
	@Autowired
	private PasswordEncDec passwordEncyption;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String submitUserForm(@ModelAttribute("userForm") @Valid User userForm,
			HttpServletRequest request, BindingResult bindingResult, ModelMap modelMap)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		String errRet = "";
		//userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "register";
		}
		if (userForm != null) {

			try {
				if (userForm.getPassword() != null) {
					userForm.setPassword(passwordEncyption.generateSecurePassword(userForm.getPassword()));
				} else {
					logger.debug("Invalid Input");
					modelMap.addAttribute("Error", "Invalid Input");
					errRet = "register";
				}

			} catch (Exception e) {
				e.printStackTrace();
				errRet = "register";
				logger.error("User Add Method --------Error " + e);
			}

			userForm.setCreateOn(new Date());
			Long id = userService.add(userForm);
			manageAddress(userForm);
			
			userService.assignUserRole(id);
			logger.debug("save new user");

			return "redirect:/login";
		} else {
			modelMap.addAttribute("Error", "Invalid Data");
			logger.debug("Invalid Data");
			errRet = "register";
		}
		return errRet;
	}

	public void manageAddress(User userForm) {
		if (userForm.getAddress() != null) {
			for (Iterator<Address> i = userForm.getAddress().iterator(); i.hasNext();) {
				Address address = i.next();

				address.setUser(userForm);
				addressService.saveOrUpdate(address);
				System.out.println(address.getUser());
			}
		}
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String viewWelcomePage(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
		SessionBean sessionBean = (SessionBean) session.getAttribute("UserSession");

		if (sessionBean != null) {
			modelMap.addAttribute("Welcome", sessionBean.getUserType() == 1 ? "Welcome To Admin" : "Welcome to User");
			return "welcome";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute("login") Login login, HttpServletRequest request, HttpSession session,
			BindingResult bindingResult, ModelMap modelMap, HttpServletResponse response)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		// loginValidator.validate(login, bindingResult);

		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("Error", "Invalid Input");
			return "login";
		}

		try {
			List<User> user = userService.getUserByEmail(login.getEmail());
			User u = null;
			if (user != null) {
				for (User todo : user) {
					u = todo;
				}
				boolean passCompare = commonUtility.passwordCompare(login.getPassword(), u.getPassword());
				if (passCompare) {
					SessionUtility.setSession(session, request, u);
					//SessionUtility.setCookies(response);
					return "redirect:/welcome";
				} else {
					modelMap.addAttribute("Error", "Invalid Email Or Password");
					return "login";
				}
			} else {
				modelMap.addAttribute("Error", "Invalid Credential");
				return "redirect:/login";
			}

		} catch (Exception e) {
			logger.error("login post call error" + e);
			modelMap.addAttribute("Error", "Internal Error" + e);
			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session, HttpServletResponse httpServletResponse) {
		//boolean isSecure = false;
		//String contextPath = null;
		if(request != null) {
			session.invalidate();
		}
		
		/*
		 * if(httpServletResponse != null) { Cookie cookie = new Cookie("UserApp", "");
		 * String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
		 * cookie.setPath(cookiePath); cookie.setMaxAge(0); cookie.setSecure(isSecure);
		 * cookie.setSecure(false); httpServletResponse.addCookie(cookie); }
		 */
		
		return "redirect:/login";
	}

	@RequestMapping(value = "/{id}/view-profile", method = RequestMethod.GET)
	public String viewUserProfile(HttpServletRequest request, @PathVariable("id") Long id, HttpSession session,
			ModelMap modelMap) {
		SessionBean sessionBean = (SessionBean) session.getAttribute("UserSession");
		if (sessionBean != null && id != 0 && id.equals(sessionBean.getUserId())) {
			List<User> userLst = userService.singleUserLst(id);
			List<Address> usrAddressLst = addressService.getAddressByUserId(id);
			modelMap.addAttribute("userLst", userLst);
			modelMap.addAttribute("usrAddressLst", usrAddressLst);
			return "viewProfile";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/{id}/edit-profile", method = RequestMethod.GET)
	public String editUser(HttpServletRequest request, @PathVariable("id") Long id, HttpSession session,
			ModelMap modelMap) {
		SessionBean sessionBean = (SessionBean) session.getAttribute("UserSession");
		if (sessionBean != null && id != 0) {
			User userForm = userService.get(id);
			setPageData(modelMap);
			modelMap.addAttribute("userForm", userForm);

			return "register";
		} else {
			modelMap.addAttribute("Error", "Invalid Session");
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/{id}/updateProfile", method = RequestMethod.POST)
	public String submitEditUser(@PathVariable Long id, @RequestBody @ModelAttribute("userForm") @Valid User userForm,
			HttpServletRequest request, HttpSession session, BindingResult bindingResult, ModelMap modelMap)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		String errRet = "";
		SessionBean sessionBean = (SessionBean) session.getAttribute("UserSession");
		if (sessionBean != null && id != 0) {
			if (bindingResult.hasErrors()) {
				errRet = "redirect:/" + id + "/edit-profile";
				logger.error("update form validation error");
				return errRet;
			}
			try {
				
				User u = userService.get(id);
				u.setUsername(userForm.getUsername());
				u.setGender(userForm.getGender());
				u.setMobile(userForm.getMobile());
				if(sessionBean.getUserType() == 1) {
					u.setEmail(userForm.getEmail());
				}
				userService.update(u);
				
				List<Long> manageAdrressLst = manageAddresses(userForm);
				
				
				if (manageAdrressLst != null) 
				  { 
					  for (Long removeAdd : manageAdrressLst) {
						  if(removeAdd !=0) {
							  Address a = addressService.get(removeAdd);
							  addressService.remove(a); 
							  logger.info("Remove Address Id" + a.getId());
						  }
					  } 
				  }
				modelMap.addAttribute("msg", "Edit Record Success");
				errRet = "redirect:/" + id + "/view-profile";
			} catch (Exception e) {
				errRet = "redirect:/" + id + "/edit-profile";
				System.out.println("submitEditUser  Method --------Error " + e);
				modelMap.addAttribute("Error", "Internal Error");
			}
		}
		return errRet;
	}

	List<Long> manageAddresses(User userForm) {
		  List<Long> ids = new ArrayList<Long>();
		  
		  if (userForm.getId() != null) { 
			  List<Address> dbAddress = addressService.getAddressByUserId(userForm.getId()); 
			  for(Address a : dbAddress) {
				  ids.add(a.getId());
			  }
		  }
			if (userForm.getAddress() != null) {
				for (Iterator<Address> i = userForm.getAddress().iterator(); i.hasNext();) {
					Address address = i.next();
					Address comAddress = null;
					
					if(address.getId() !=0) {
						if(ids.contains(address.getId())) {
							comAddress = addressService.get(address.getId());
							if(comAddress != null) {
								if(address.getCity() != null && address.getPinCode() != null && address.getState() !=null) {
									address.setUser(comAddress.getUser());
									addressService.update(address);
									ids.remove(address.getId());
								}
							}
						}else {
							ids.remove(address.getId());
						}
					}else {
						address.setUser(userForm);
						addressService.add(address);
						logger.info("Add New Address ----");
					}
				}
			}
		return ids;
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(ModelMap modelMap) {
		modelMap.addAttribute("forget", new Login());
		return "forgotPassword";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(@RequestParam String email, ModelMap modelMap) {

		String response = userService.forgotPassword(email);

		if (!response.startsWith("Invalid")) {
			response = "redirect:/reset-password/" + response;
		} else {
			modelMap.addAttribute("Error", "Invalid Email ID");
			response = "redirect:/forgotPassword";
		}
		return response;
	}

	@RequestMapping(value = "/reset-password/{token}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable String token, ModelMap modelMap) {
		modelMap.addAttribute("resetModel", new User());
		modelMap.addAttribute("token", token);
		return "resetPassword";
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String resetPassword(@RequestParam("hdToken") String token, @RequestParam("password") String password,
			@RequestParam("conPassword") String conPassword, ModelMap modelMap) {
		if (password.equals(conPassword)) {
			String ret = userService.resetPassword(token, password);
			modelMap.addAttribute("msg", ret);
		} else {
			modelMap.addAttribute("Error", "Password Not Match");
		}
		return "resetPassword";
	}

	public void setPageData(ModelMap model) {
		model.addAttribute("genderOptions", User.getGenderOptions());
	}
	
	@RequestMapping(value = "/{id}/delete-profile", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, @PathVariable("id") Long id, HttpSession session, ModelMap modelMap) {
		SessionBean sessionBean = (SessionBean) session.getAttribute("UserSession");
		if (sessionBean != null && id != 0 && sessionBean.getUserType() == 1) {
			User userForm = userService.get(id);
			if(userForm != null) {
				userService.remove(userForm);
				modelMap.addAttribute("msg", "Delete User Success");
			}else {
				modelMap.addAttribute("Error", "Record Not Found");
			}
			return "redirect:/admin/view-user-list";
		} else {
			modelMap.addAttribute("Error", "Invalid Session OR Input");
			return "redirect:/login";
		}
	}
}