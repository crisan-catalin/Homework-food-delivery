package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ContactDetailsDto;
import com.example.fooddelivery.dto.OrderEntriesByRestaurantDto;
import com.example.fooddelivery.dto.OrderListDto;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * OrderService for basic order operations.
 */
public interface OrderService {

    /**
     * Update order status
     *
     * @param orderId order id
     * @param status  order new status
     * @throws EntityNotFoundException
     */
    void updateOrderStatus(Long orderId, DeliveryStatus status) throws EntityNotFoundException;

    /**
     * Get all orders with status {@link com.example.fooddelivery.enums.DeliveryStatus} PLACED
     *
     * @return list of {@link OrderListDto}
     */
    List<OrderListDto> getOpenOrders();

    /**
     * Get order for id
     *
     * @param orderId
     */
    Optional<Order> getOrder(Long orderId);

    /**
     * Create contact details dto based on order information
     *
     * @param order order
     * @return {@link ContactDetailsDto}
     */
    ContactDetailsDto getContactDetails(Order order);

    /**
     * Create {@link OrderEntriesByRestaurantDto}s based on order entries
     *
     * @param orderEntries order entries
     * @return {@link Set<OrderEntriesByRestaurantDto>}
     */
    Set<OrderEntriesByRestaurantDto> getOrderEntriesByRestaurantDtos(Set<OrderEntry> orderEntries);
}
