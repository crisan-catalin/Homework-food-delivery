package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.service.impl.DefaultProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    DefaultProductService defaultProductService;

    @GetMapping("/{restaurantId}/products")
    public List<ProductWithQuantityDto> getProductsForRestaurant(@PathVariable("restaurantId") String restaurantId) {
        return defaultProductService.getProductsForRestaurantId(Long.parseLong(restaurantId));
    }
}
