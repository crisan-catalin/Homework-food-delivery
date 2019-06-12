package com.example.fooddelivery.dto;

import java.util.Set;

import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;

public class OrderDto {

    private Long id;
    private User customer;
    private User livrator;
    private Long totalPrice;
    private Address deliveryAddress;
    private DeliveryStatus deliveryStatus;
    private Set<OrderEntry> orderEntries;

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getLivrator() {
        return livrator;
    }

    public void setLivrator(User livrator) {
        this.livrator = livrator;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Set<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Set<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
