package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    ProductFacade productFacade;

    @GetMapping("/{restaurantId}/products")
    public List<ProductWithQuantityDto> getProductsForRestaurant(@PathVariable("restaurantId") String restaurantId) {
        return productFacade.getProductsForRestaurantId(Long.parseLong(restaurantId));
    }
}
