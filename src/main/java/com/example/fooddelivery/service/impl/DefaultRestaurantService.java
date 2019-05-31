package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.dto.RestaurantDto;
import com.example.fooddelivery.repository.RestaurantRepository;
import com.example.fooddelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultRestaurantService implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    @Override
    public List<RestaurantDto> getRestaurantsNameAndId() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> new RestaurantDto(restaurant.getId(), restaurant.getName()))
                .collect(Collectors.toList());
    }
}