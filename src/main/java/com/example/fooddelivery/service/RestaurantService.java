package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.dto.RestaurantDto;
import com.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;


    public List<RestaurantDto> getRestaurantsName() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> new RestaurantDto(restaurant.getId(), restaurant.getName()))
                .collect(Collectors.toList());
    }
}
