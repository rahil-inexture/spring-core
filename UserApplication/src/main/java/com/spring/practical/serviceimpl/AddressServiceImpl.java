package com.spring.practical.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practical.dao.AddressDao;
import com.spring.practical.model.Address;
import com.spring.practical.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	final static Logger log = Logger.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressDao addressDao;
		
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(Address address) {
		addressDao.saveOrUpdate(address);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> getAll() {
		log.debug("getting all Address");
		return addressDao.getAll();
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Address get(Long id) {
		return addressDao.find(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(Address entity) {
		Long id = addressDao.add(entity);
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Address entity) {
		addressDao.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Address entity) {
		addressDao.remove(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Address> getAddressByUserId(Long id) {
		return addressDao.getAddressByUserId(id);
	}	
}