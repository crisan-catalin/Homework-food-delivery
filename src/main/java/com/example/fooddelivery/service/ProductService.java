package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ProductWithQuantityDto;

import java.util.List;

/**
 * ProductService used for product operations.
 */
public interface ProductService {

    /**
     * Returns all products for a restaurant id
     * @param restaurantId restaurant id
     * @return list of {@link ProductWithQuantityDto} for a restaurant
     */
    List<ProductWithQuantityDto> getProductsForRestaurantId(Long restaurantId);
}
