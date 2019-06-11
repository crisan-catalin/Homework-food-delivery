package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.model.Product;

import java.util.List;

/**
 * ProductService used for product operations.
 */
public interface ProductService {

    /**
     * Returns all products for the restaurant id
     *
     * @param restaurantId the restaurant id
     * @return list of {@link Product} for the restaurant is
     */
    List<Product> getProductsForRestaurantId(Long restaurantId);
}
