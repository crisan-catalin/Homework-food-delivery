package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.repository.ProductRepository;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductsForRestaurantId(Long restaurantId) {
        return productRepository.findAllByRestaurantId(restaurantId);
    }

    public OrderEntry generateOrderEntry(Long productId, Integer quantity) throws EntityNotFoundException {
        final Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);

        final OrderEntry orderEntry = new OrderEntry();
        orderEntry.setProduct(product);
        orderEntry.setQuantity(quantity);
        return orderEntry;
    }
}
