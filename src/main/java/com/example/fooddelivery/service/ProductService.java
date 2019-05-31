package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductWithQuantityDto> getProductsForRestaurantId(Long restaurantId) {
        return productRepository.findAllByRestaurant_Id(restaurantId).stream()
                .map(product -> new ProductWithQuantityDto(product.getId(), product.getName(), product.getPrice(), 0))
                .collect(Collectors.toList());
    }
}