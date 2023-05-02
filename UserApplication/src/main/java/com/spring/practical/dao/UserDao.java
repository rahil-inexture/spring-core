package com.spring.practical.dao;

import java.util.List;

import com.spring.practical.model.User;

public interface UserDao extends GenericDao<User, Long>{
	boolean emailIdExist(String email);
	public List<User> getUserByEmail(String email);
	public List<User> findByToken(String token);
	public List<User> singleUserLst(Long id);
}
