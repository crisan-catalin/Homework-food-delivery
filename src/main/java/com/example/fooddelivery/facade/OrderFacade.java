package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.OrderDetailsDto;
import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.forms.AddressForm;

import java.util.List;

public interface OrderFacade {

    /**
     * Create order based on user form
     *
     * @param products list of {@link ProductSessionDto} products
     * @param address  delivery address
     */
    void createOrder(List<ProductSessionDto> products, AddressForm address);

    /**
     * Create dto based on order information
     *
     * @param orderId order id
     * @return {@link OrderDetailsDto}
     * @throws EntityNotFoundException when order id is invalid
     */
    OrderDetailsDto getOrderDetails(Long orderId) throws EntityNotFoundException;
}
