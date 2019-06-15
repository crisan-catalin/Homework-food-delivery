package com.example.fooddelivery.dto;

public class OrderListDto {
    private Long id;
    private String customerName;
    private Long totalPrice;
    private AddressDto deliveryAddress;

    public OrderListDto(Long id, String customerName, Long totalPrice, AddressDto deliveryAddress) {
        this.id = id;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public AddressDto getDeliveryAddress() {
        return deliveryAddress;
    }
}
