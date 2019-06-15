package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.OrderListDto;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * OrderService for basic order operations.
 */
public interface OrderService {

    /**
     * Create an order based on the customer, address and order entries
     *
     * @param customer        the user
     * @param customerAddress the address
     * @param entries         the order entries
     */
    void createOrder(User customer, Address customerAddress, Set<OrderEntry> entries);

    /**
     * Update order status
     *
     * @param livratorId
     * @param orderId    order id
     * @throws EntityNotFoundException
     */
    void startOrderProcessing(Long livratorId, Long orderId) throws EntityNotFoundException;

    /**
     * Get all orders with status {@link com.example.fooddelivery.enums.DeliveryStatus} PLACED
     *
     * @return list of {@link OrderListDto}
     */
    List<Order> getOpenOrders();

    /**
     * Get order for id
     *
     * @param orderId the order id
     * @return optional of {@link Order}
     */
    Optional<Order> getOrder(Long orderId);

    /**
     * Get all entries of an order grouped by restaurants name
     *
     * @param orderEntries the order entries
     * @return dictionary of restaurants name and entries for each restaurant
     */
    Map<String, Set<OrderEntry>> getOrderEntriesGroupedByRestaurantName(Set<OrderEntry> orderEntries);
}
