package com.example.fooddelivery.service;

import com.example.fooddelivery.model.Restaurant;
import com.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<String> getRestaurantsName() {
        return restaurantRepository.findAll().stream()
                .map(Restaurant::getName)
                .collect(Collectors.toList());
    }
}
