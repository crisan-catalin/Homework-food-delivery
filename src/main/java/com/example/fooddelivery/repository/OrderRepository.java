package com.example.fooddelivery.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.fooddelivery.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> getOrdersByCustomerId(Long id);

    List<Order> getOrdersByLivratorId(Long id);
}
