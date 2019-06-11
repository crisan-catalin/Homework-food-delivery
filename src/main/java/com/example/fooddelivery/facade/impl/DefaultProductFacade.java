package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.facade.ProductFacade;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultProductFacade implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductWithQuantityDto> getProductsForRestaurantId(Long restaurantId) {
        return productService.getProductsForRestaurantId(restaurantId).stream()
                .map(product -> new ProductWithQuantityDto(product.getId(), product.getName(), product.getPrice(), 0))
                .collect(Collectors.toList());
    }
}
