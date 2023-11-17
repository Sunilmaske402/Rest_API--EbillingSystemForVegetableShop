package com.ebilling.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebilling.shop.entity.OrderedItem;

public interface OrderedItemReposotory extends JpaRepository<OrderedItem, Integer> {

}
