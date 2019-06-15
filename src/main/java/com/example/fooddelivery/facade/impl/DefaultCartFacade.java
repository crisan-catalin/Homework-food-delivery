package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.facade.CartFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.*;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.ProductRepository;
import com.example.fooddelivery.repository.UserRepository;
import com.example.fooddelivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
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
