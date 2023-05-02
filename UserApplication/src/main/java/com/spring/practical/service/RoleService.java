package com.spring.practical.service;

import java.util.List;

import com.spring.practical.model.Role;

public interface RoleService {
	
	public void saveOrUpdate(Role entity);
    public List<Role> getAll();
    public Long add(Role entity);
    public void update(Role entity);
    public void remove(Role entity);
    List<Role> findByName(String roleName);
    public void addUserRole(Long userId, Long roleId);
    public Long getRolesByUserId(Long id);
    public Role findById(Long id);
    
}
