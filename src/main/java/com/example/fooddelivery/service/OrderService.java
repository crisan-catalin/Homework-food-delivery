package com.example.fooddelivery.service;

import java.util.List;

import com.example.fooddelivery.model.Order;

/**
 * OrderService for order operations.
 */
public interface OrderService {

    /**
     * Get orders for a given user id
     *
     * @param id the user id
     * @return {@link List<Order>} orders
     */
    List<Order> getOrdersByUserId(Long id);

    /**
     * Get orders for a given livrator id
     *
     * @param id the livrator id
     * @return {@link List<Order>} orders
     */
    List<Order> getOrdersByLivratorId(Long id);
}
