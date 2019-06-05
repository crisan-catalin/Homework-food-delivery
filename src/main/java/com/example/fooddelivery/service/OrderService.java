package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.OrderListDto;

import java.util.List;

/**
 * OrderService for basic order operations.
 */
public interface OrderService {

    /**
     * Get all orders with status {@link com.example.fooddelivery.enums.DeliveryStatus} PLACED
     *
     * @return list of {@link OrderListDto}
     */
    List<OrderListDto> getOpenOrders();
}
