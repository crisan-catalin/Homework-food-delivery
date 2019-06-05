package com.example.fooddelivery.dto;

import java.util.Set;

public class OrderDetailsDto {

    private Boolean orderAvailable;
    private ContactDetailsDto customerDetails;
    private Set<OrderEntriesByRestaurantDto> orderEntriesByRestaurant;
    private Long totalPrice;

    public Boolean getOrderAvailable() {
        return orderAvailable;
    }

    public void setOrderAvailable(Boolean orderAvailable) {
        this.orderAvailable = orderAvailable;
    }

    public ContactDetailsDto getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(ContactDetailsDto customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Set<OrderEntriesByRestaurantDto> getOrderEntriesByRestaurant() {
        return orderEntriesByRestaurant;
    }

    public void setOrderEntriesByRestaurant(Set<OrderEntriesByRestaurantDto> orderEntriesByRestaurant) {
        this.orderEntriesByRestaurant = orderEntriesByRestaurant;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
