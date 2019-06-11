package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.ProductWithQuantityDto;

import java.util.List;

/**
 * Used for general product operations
 */
public interface ProductFacade {
    /**
     * Returns all product dtos for the restaurant id
     *
     * @param restaurantId the restaurant id
     * @return list of {@link ProductWithQuantityDto} for the restaurant id
     */
    List<ProductWithQuantityDto> getProductsForRestaurantId(Long restaurantId);
}
