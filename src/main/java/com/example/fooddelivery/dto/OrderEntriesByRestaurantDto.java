package com.example.fooddelivery.dto;

import java.util.Set;

public class OrderEntriesByRestaurantDto {
    private Set<ProductWithQuantityDto> products;
    private String restaurantName;

    public Set<ProductWithQuantityDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductWithQuantityDto> products) {
        this.products = products;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
