package com.example.fooddelivery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.OrderService;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        return orderRepository.getOrdersByCustomerId(id);
    }

    @Override
    public List<Order> getOrdersByLivratorId(Long id) {
        return orderRepository.getOrdersByLivratorId(id);
    }
}
