package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.OrderEntry;
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
     * @return list of {@link Product} for the restaurant id
     */
    List<Product> getProductsForRestaurantId(Long restaurantId);

    /**
     * Generate an order entry based on the product id and the quantity
     *
     * @param productId the product id
     * @param quantity  the quantity
     * @return an {@link OrderEntry} with product and quantity
     */
    OrderEntry generateOrderEntry(Long productId, Integer quantity) throws EntityNotFoundException;
}
