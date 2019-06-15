package com.example.fooddelivery.dto;

public class ContactDetailsDto {
    private String customerName;
    private String customerPhone;
    private AddressDto address;

    public ContactDetailsDto(String customerName, String customerPhone, AddressDto address) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public AddressDto getAddress() {
        return address;
    }
}
