package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    ProductService productService;

    @GetMapping("/{restaurantId}/products")
    public List<ProductWithQuantityDto> getProductsForRestaurant(@PathVariable("restaurantId") String restaurantId) {
        return productService.getProductsForRestaurantId(Long.parseLong(restaurantId));
    }
}
