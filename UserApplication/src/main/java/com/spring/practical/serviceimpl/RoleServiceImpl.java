package com.spring.practical.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.RoleDao;
import com.spring.practical.model.Role;
import com.spring.practical.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	final static Logger log = Logger.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDao roleDao;
		
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Role u) {
		roleDao.saveOrUpdate(u);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Role> getAll() {
		log.debug("getting all users");
		return roleDao.getAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(Role entity) {
		Long id = roleDao.add(entity);
		log.debug("save new user");
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Role entity) {
		roleDao.update(entity);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Role entity) {
		roleDao.remove(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Role> findByName(String roleName) {
		return roleDao.findByName(roleName);
	}	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addUserRole(Long userId, Long roleId) {
		roleDao.addUserRole(userId, roleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long getRolesByUserId(Long id) {
		return roleDao.getRolesByUserId(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Role findById(Long id) {
		return roleDao.find(id);
	}
}
