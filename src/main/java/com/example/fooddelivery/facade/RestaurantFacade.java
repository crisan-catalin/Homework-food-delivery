package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.RestaurantDto;

import java.util.List;

/**
 * Used for general restaurant operations
 */
public interface RestaurantFacade {

    /**
     * Get all restaurant dtos with name and id
     *
     * @return list of {@link RestaurantDto}
     */
    List<RestaurantDto> getRestaurantsNameAndId();
}
