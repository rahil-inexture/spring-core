package com.spring.practical.daoimpl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.RoleDao;
import com.spring.practical.model.Role;

@Repository
@Transactional
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByName(String roleName) {
		String hql = "select r from Role r where r.name = :roleName";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("roleName", roleName);
		
		List<Role> role = qry.getResultList();
		return role;
	}

	@Override
	public void addUserRole(Long userId, Long roleId) {
		String sql = "INSERT INTO user_role (user_id, role_id) VALUES ("+ userId + ","+ roleId +")";
		Query qry = this.getSession().createSQLQuery(sql);
		qry.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getRolesByUserId(Long id) {
		String hql = "select ur.role_id from user_role ur" + " where ur.user_id=:userID";
		Query query = getSession().createSQLQuery(hql);
		query.setParameter("userID", id);
		
		List result = query.getResultList();
		Long rId = null;
		if(result != null) {
			rId = Long.parseLong(result.get(0).toString());
		}
		return rId;
	}
}
