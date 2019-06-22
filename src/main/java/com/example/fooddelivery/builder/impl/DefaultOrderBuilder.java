package com.example.fooddelivery.builder.impl;

import com.example.fooddelivery.builder.OrderBuilder;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;

import java.util.Set;

public class DefaultOrderBuilder implements OrderBuilder {

    private User customer;
    private Address deliveryAddress;
    private Set<OrderEntry> entries;

    @Override
    public OrderBuilder setCustomer(User customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public OrderBuilder setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    @Override
    public OrderBuilder setEntries(Set<OrderEntry> entries) {
        this.entries = entries;
        return this;
    }

    @Override
    public Order build() {
        final Order order = new Order();
        order.setCustomer(customer);
        order.setDeliveryAddress(deliveryAddress);
        order.setDeliveryStatus(DeliveryStatus.PLACED);

        entries.forEach(entry -> entry.setOrder(order));
        order.setOrderEntries(entries);

        final Long orderTotalPrice = entries.stream()
                .mapToLong(entry -> entry.getProduct().getPrice() * entry.getQuantity()).sum();
        order.setTotalPrice(orderTotalPrice);
        return order;
    }
}
