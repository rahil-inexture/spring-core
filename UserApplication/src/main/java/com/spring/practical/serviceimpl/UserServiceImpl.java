package com.spring.practical.serviceimpl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.RoleDao;
import com.spring.practical.dao.UserDao;
import com.spring.practical.model.Role;
import com.spring.practical.model.User;
import com.spring.practical.model.UserRole;
import com.spring.practical.service.UserService;
import com.spring.practical.utility.PasswordEncDec;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	final static Logger log = Logger.getLogger(UserServiceImpl.class);
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PasswordEncDec passwordEncyption;
	
	//Email Exist Or Not
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean findByEmail(String email) {
		return userDao.emailIdExist(email);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(User u) {
		userDao.saveOrUpdate(u);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> getAll() {
		log.debug("getting all users");
		return userDao.getAll();
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User get(Long id) {
		return userDao.find(id);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(User entity) {
		Long id = userDao.add(entity);
		return id;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(User entity) {
		userDao.remove(entity);
	}	
	
	@Override
	public List<User> getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void assignUserRole(Long userId) {
		List<Role> defaultRole = roleDao.findByName(UserRole.ROLE_USER.toString());
		roleDao.addUserRole(userId, defaultRole.get(0).getId());
	}

	@Override
	public User findByToken(String token) {
		return null;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String forgotPassword(String email) {
		List<User> userOptional = userDao.getUserByEmail(email);
		if(userOptional == null) {
			return "Invalid email";
		}
		
		User user = new User();
		for(User uInfo : userOptional) {
			user = uInfo;
		}		
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());
		userDao.add(user);

		return user.getToken();
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String resetPassword(String token, String password) {
		List<User> userOptional = userDao.findByToken(token);
		String retVal = "";
		if (userOptional == null) {
			return "Invalid token.";
		}

		User user = new User();
		for(User uInfo : userOptional) {
			user = uInfo;
		}
		LocalDateTime tokenCreationDate = user.getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			retVal = "Token expired.";
			return retVal;
		}	
		
		try {
			user.setPassword(passwordEncyption.generateSecurePassword(password));
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e1) {
			log.error("Password Encryption Problem");
			e1.printStackTrace();
		}
		
		user.setToken(null);
		user.setTokenCreationDate(null);
		userDao.add(user);
		retVal = "Your password successfully updated.";
		return retVal;
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	@Override
	public List<User> singleUserLst(Long id) {
		return userDao.singleUserLst(id);
	}
}