package com.spring.practical.dao;

import java.util.List;

import com.spring.practical.model.Role;

public interface RoleDao extends GenericDao<Role, Long>{
	List<Role> findByName(String string);
	void addUserRole(Long userId, Long roleId);
	public Long getRolesByUserId(Long id);
}
