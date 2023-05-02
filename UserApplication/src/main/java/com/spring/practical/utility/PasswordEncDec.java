package com.spring.practical.utility;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncDec {
	private static final Logger logger = Logger.getLogger(PasswordEncDec.class);
	
	
	public String generateSecurePassword(String originString) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException{
		final String generatePassword = generateHash(originString); 
		
		logger.info("Password Encrypt Success..");
		return generatePassword;
	}

	private String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException{
		int iteration = 101;
		char []chars = password.toCharArray();
		byte []salt = getSalt();
		
		  PBEKeySpec spec = new PBEKeySpec(chars, salt, iteration, 64 * 8);
		  SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		  byte[] hash = skf.generateSecret(spec).getEncoded();
		  return iteration + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private byte[] getSalt() throws NoSuchAlgorithmException{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	private String toHex(byte[] arr) throws NoSuchAlgorithmException{
		BigInteger bi = new BigInteger(1, arr);
	    String hex = bi.toString(16);
	    
	    int len = (arr.length * 2) - hex.length();
	    if(len > 0)
	    {
	        return String.format("%0"  + arr + "d", 0) + hex;
	    }else{
	        return hex;
	    }
	}
	
	protected boolean matchPassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException{
		String[] parts = storedPassword.split(":");
	    int iterations = Integer.parseInt(parts[0]);

	    byte[] salt = fromHex(parts[1]);
	    byte[] hash = fromHex(parts[2]);
	    
	    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), 
	            salt, iterations, hash.length * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] testHash = skf.generateSecret(spec).getEncoded();
	        
	        int diff = hash.length ^ testHash.length;
	        for(int i = 0; i < hash.length && i < testHash.length; i++)
	        {
	            diff |= hash[i] ^ testHash[i];
	        }
	        return diff == 0;
	}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
	    byte[] bytes = new byte[hex.length() / 2];
	    for(int i = 0; i < bytes.length ;i++)
	    {
	        bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	    }
	    return bytes;
	}
}
