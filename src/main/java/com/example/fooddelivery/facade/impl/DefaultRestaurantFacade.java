package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.RestaurantDto;
import com.example.fooddelivery.facade.RestaurantFacade;
import com.example.fooddelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultRestaurantFacade implements RestaurantFacade {

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public List<RestaurantDto> getRestaurantsNameAndId() {
        return restaurantService.findAll().stream()
                .map(restaurant -> new RestaurantDto(restaurant.getId(), restaurant.getName()))
                .collect(Collectors.toList());
    }
}
