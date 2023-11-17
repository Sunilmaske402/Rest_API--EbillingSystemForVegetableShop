package com.ebilling.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findAllByShopownerId(int id);

	List<Customer> findAllByName(String name);

}
