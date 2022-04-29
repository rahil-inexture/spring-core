package com.spring.boot.auth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.auth2.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
