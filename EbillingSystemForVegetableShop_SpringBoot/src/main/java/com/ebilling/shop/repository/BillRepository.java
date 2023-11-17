package com.ebilling.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Bill_Order;
public interface BillRepository extends JpaRepository<Bill_Order, Integer> {

	List<Bill_Order> findAllById(int id);


	List<Bill_Order> findByCustomerId(int id);


}
