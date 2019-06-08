package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.RestaurantDto;

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
    List<RestaurantDto> getRestaurantsNameAndId();
}
