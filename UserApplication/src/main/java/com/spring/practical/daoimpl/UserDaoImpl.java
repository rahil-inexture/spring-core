package com.spring.practical.daoimpl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.UserDao;
import com.spring.practical.model.User;

@Repository
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao{

	@Override
	public boolean emailIdExist(String email) {
		String hql = "select usr.email from User usr where usr.email = :email";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("email", email);
		
		return qry.getFirstResult() > 0 ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByEmail(String email) {
		String hql = "select usr from User usr where usr.email = :email";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("email", email);
		
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByToken(String token) {
		String hql = "select usr from User usr where usr.token = :token";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("token", token);
		
		return qry.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> singleUserLst(Long id){
		String hql = "select usr from User usr where usr.id = :id";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("id", id);
		
		return qry.getResultList();
	}
}