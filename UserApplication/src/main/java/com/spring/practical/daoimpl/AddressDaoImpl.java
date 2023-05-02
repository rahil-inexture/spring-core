package com.spring.practical.daoimpl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.AddressDao;
import com.spring.practical.model.Address;

@Repository
@Transactional
public class AddressDaoImpl extends GenericDaoImpl<Address, Long> implements AddressDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressByUserId(Long id) {
		String hql = "select a from Address a where a.user.id = :id";
		Query qry = this.getSession().createQuery(hql);
		qry.setParameter("id", id);	
		
		return qry.getResultList();
	}
}
