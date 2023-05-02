package com.spring.practical.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.practical.model.Login;

@Component
public class LoginValidator implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return Login.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		Login login = (Login) object;
		
		/* email validation */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "field.empty");
		if(login.getEmail().length() < 5 || login.getEmail().length() > 120) {
			errors.reject("size.userform.email");
		}
		
		/* password compare */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "field.empty");
		if(login.getPassword().length() < 6 || login.getPassword().length() > 20) {
			errors.reject("size.userform.password");
		}
	}
}
