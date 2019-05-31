package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.facade.CartFacade;

import java.util.List;
import java.util.Optional;

public class DefaultCartFacade implements CartFacade {

    @Override
    public List<ProductSessionDto> mergeWithExistingProducts(List<ProductSessionDto> productsFromSession, List<ProductSessionDto> newProducts) {
        for (ProductSessionDto product : newProducts) {
            Optional<ProductSessionDto> existingProduct = productsFromSession.stream()
                    .filter(p -> product.getId().equals(p.getId()))
                    .findFirst();
            if (existingProduct.isPresent()) {
                ProductSessionDto productFromSession = existingProduct.get();
                productFromSession.setQuantity(productFromSession.getQuantity() + product.getQuantity());
            } else {
                productsFromSession.add(product);
            }
        }

        return productsFromSession;
    }
}
