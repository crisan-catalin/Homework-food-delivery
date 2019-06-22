package com.example.fooddelivery.builder;

import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;

import java.util.Set;

/**
 * OrderBuilder for create a new order
 */
public interface OrderBuilder {

    /**
     * Sets customer for the order
     *
     * @param customer the customer entity
     * @return this {@link OrderBuilder}
     */
    OrderBuilder setCustomer(User customer);

    /**
     * Sets delivery address for the order
     *
     * @param deliveryAddress the address entity
     * @return this {@link OrderBuilder}
     */
    OrderBuilder setDeliveryAddress(Address deliveryAddress);

    /**
     * Sets product entries for the order
     *
     * @param entries the order entries entity collection
     * @return this {@link OrderBuilder}
     */
    OrderBuilder setEntries(Set<OrderEntry> entries);

    /**
     * Returns an order built from the parameters setted. Order have {@link com.example.fooddelivery.enums.DeliveryStatus#PLACED} and {@link Order#totalPrice} calculated from entries total price.
     *
     * @return an order built with parameters of this {@link OrderBuilder}
     */
    Order build();
}
