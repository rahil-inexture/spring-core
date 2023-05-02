package com.spring.practical.dao;

import java.util.List;

import com.spring.practical.model.Address;

public interface AddressDao extends GenericDao<Address, Long>{
	public List<Address> getAddressByUserId(Long id);
}
