package com.example.fooddelivery.dto;

public class AddressDto {
    private String city;
    private String street;
    private String number;

    public AddressDto(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }
}
