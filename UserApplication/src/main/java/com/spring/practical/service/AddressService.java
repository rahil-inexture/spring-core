package com.spring.practical.service;

import java.util.List;

import com.spring.practical.model.Address;

public interface AddressService {
	
	public void saveOrUpdate(Address entity);
    public List<Address> getAll();
    public Address get(Long id);
    public Long add(Address entity);
    public void update(Address entity);
    public void remove(Address entity);
    public List<Address> getAddressByUserId(Long id);
}
