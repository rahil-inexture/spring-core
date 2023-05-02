package com.spring.practical.service;

import java.util.List;

import com.spring.practical.model.User;

public interface UserService {
	
	public void saveOrUpdate(User u);
    public List<User> getAll();
    public User get(Long id);
    public Long add(User entity);
    public void update(User entity);
    public void remove(User entity);
    public boolean findByEmail(String email);
    public List<User> getUserByEmail(String email);
    public void assignUserRole(Long userId);
    public User findByToken(String token);
	public String forgotPassword(String email);
	public String resetPassword(String token, String password);
	public List<User> singleUserLst(Long id);
}
