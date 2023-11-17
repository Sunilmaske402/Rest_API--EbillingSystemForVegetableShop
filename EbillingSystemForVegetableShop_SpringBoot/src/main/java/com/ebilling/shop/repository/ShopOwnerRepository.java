package com.ebilling.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.Shopowner;

public interface ShopOwnerRepository extends JpaRepository<Shopowner, Integer> {

	List<Shopowner> findAllByName(String name);

}
