package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.OrderDetailsDto;
import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.OrderListDto;
import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.forms.AddressForm;

import java.util.List;

/**
 * OrderFacade used for general order operations.
 */
public interface OrderFacade {

    /**
     * Start the order processing by setting the {@link com.example.fooddelivery.enums.DeliveryStatus#IN_PROGRESS} and the livrator based on id
     *
     * @param livratorId the livrator id
     * @param orderId the order id
     * @throws EntityNotFoundException when the livrator or the order is not found
     */
    void startOrderProcessing(Long livratorId, Long orderId) throws EntityNotFoundException;

    /**
     * Get all orders with status {@link com.example.fooddelivery.enums.DeliveryStatus#PLACED}
     *
     * @return list of {@link OrderListDto}
     */
    List<OrderListDto> getOpenOrders();

    /**
     * Create an order based on the user id, products and user address
     *
     * @param userId the customer id
     * @param products the products
     * @param address the customer address
     * @throws EntityNotFoundException when customer is not found
     */
    void createOrder(Long userId, List<ProductSessionDto> products, AddressForm address) throws EntityNotFoundException;

    /**
     * Create dto based on order information
     *
     * @param orderId order id
     * @return {@link OrderDetailsDto}
     * @throws EntityNotFoundException when order id is invalid
     */
    OrderDetailsDto getOrderDetails(Long orderId) throws EntityNotFoundException;

    /**
     * Get user orders
     *
     * @param user the user to retrieve orders for
     * @return {@link List< OrderDto >} orders
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
