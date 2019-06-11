package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.repository.ProductRepository;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductsForRestaurantId(Long restaurantId) {
        return productRepository.findAllByRestaurantId(restaurantId);
    }
}
