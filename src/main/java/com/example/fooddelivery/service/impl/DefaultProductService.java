package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.repository.ProductRepository;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductWithQuantityDto> getProductsForRestaurantId(Long restaurantId) {
        return productRepository.findAllByRestaurantId(restaurantId).stream()
                .map(product -> new ProductWithQuantityDto(product.getId(), product.getName(), product.getPrice(), 0))
                .collect(Collectors.toList());
    }
}
