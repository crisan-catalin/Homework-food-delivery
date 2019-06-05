package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.ProductSessionDto;

import java.util.List;

/**
 * CartFacade used for general cart operations.
 */
public interface CartFacade {

    /**
     * Merging product quantity if a product already exists otherwise add the product
     *
     * @param productsFromSession products from session
     * @param newProducts         products to be added to session
     * @return merged list with all all products from session
     */
    List<ProductSessionDto> mergeWithExistingProducts(List<ProductSessionDto> productsFromSession, List<ProductSessionDto> newProducts);
}
