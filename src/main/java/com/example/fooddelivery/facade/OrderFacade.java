package com.example.fooddelivery.facade;

import java.util.List;

import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.SessionUserDto;

/**
 * OrderFacade used for general order operations.
 */
public interface OrderFacade {

    /**
     * Get user orders
     *
     * @param user the user to retrieve orders for
     * @return {@link List<OrderDto>} orders
     */
    List<OrderDto> getOrders(SessionUserDto user);

    /**
     * Get user pending orders
     *
     * @param user the user to retrieve orders for
     * @return {@link List<OrderDto>} orders
     */
    List<OrderDto> getPendingOrders(SessionUserDto user);

    /**
     * Get user taken orders
     *
     * @param user the user to retrieve orders for
     * @return {@link List<OrderDto>} orders
     */
    List<OrderDto> getTakenOrders(SessionUserDto user);
}
