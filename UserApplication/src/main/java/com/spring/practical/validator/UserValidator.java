package com.spring.practical.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.practical.model.User;
import com.spring.practical.service.UserService;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserService userService;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		
		/* email validation */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "field.empty");
		if(user.getEmail().length() < 5 || user.getEmail().length() > 120) {
			errors.reject("size.userform.email");
		}else {
			if(userService.findByEmail(user.getEmail())){
				errors.rejectValue("email","duplicate.userform.email");
			}
		}
		
		/* name validation */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "field.empty");
		if(user.getUsername().length() < 3 || user.getUsername().length() > 50) {
			errors.rejectValue("username","size.userform.username");
		}
		
		
		/* password compare */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "field.empty");
		if(user.getUsername().length() < 6 || user.getUsername().length() > 20) {
			errors.reject("size.userform.password");
		}else {
			if (!user.getConPassword().equals(user.getPassword())) {
	            errors.rejectValue("conPassword", "diff.userform.password.confirm");
	        }
		}
	}

}
