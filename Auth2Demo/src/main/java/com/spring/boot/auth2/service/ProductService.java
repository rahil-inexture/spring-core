package com.spring.boot.auth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.auth2.dao.ProductRepository;
import com.spring.boot.auth2.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	
	public List<Product> listAll(){
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(Long id) {
		return repo.getById(id);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
