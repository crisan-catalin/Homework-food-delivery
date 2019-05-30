package com.example.fooddelivery.forms;

import java.util.List;

public class OrderForm {

    private AddressForm address;
    private List<ProductForm> products;

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public List<ProductForm> getProducts() {
        return products;
    }

    public void setProducts(List<ProductForm> products) {
        this.products = products;
    }
}
