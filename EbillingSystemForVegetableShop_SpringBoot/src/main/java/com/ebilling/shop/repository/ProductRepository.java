package com.ebilling.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllById(int id);

	List<Product> findAllByShopownerId(int id);

}
