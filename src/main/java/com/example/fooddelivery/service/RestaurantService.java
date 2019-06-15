package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.RestaurantDto;
import com.example.fooddelivery.model.Restaurant;

import java.util.List;

/*
 * RestaurantService used for product operations.
 */
public interface RestaurantService {

    /**
     * Get all restaurants name and id
     *
     * @return list of {@link RestaurantDto}
     */
    List<Restaurant> findAll();
}
