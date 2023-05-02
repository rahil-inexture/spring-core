package com.spring.practical.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonUtility {

	@Autowired
	private PasswordEncDec passwordEncDec;

	
	public boolean passwordCompare(String password, String encPass) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		return passwordEncDec.matchPassword(password, encPass);
	}
}
