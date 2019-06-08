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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressService addressService;

    public void createOrder(List<ProductSessionDto> products, AddressForm address) {
        Order order = new Order();
        order.setDeliveryStatus(DeliveryStatus.PLACED);
        order.setOrderEntries(new HashSet<>());

        //TODO: FIX it after @Colea implement user login
        final User customer = userRepository.findById(1L)
                .orElseThrow(EntityExistsException::new);
        order.setCustomer(customer);

        Long orderTotalPrice = 0L;
        for (ProductSessionDto sessionProduct : products) {
            final OrderEntry orderEntry = createOrderEntry(order, sessionProduct);
            orderTotalPrice += sessionProduct.getPrice() * sessionProduct.getQuantity();
            order.getOrderEntries().add(orderEntry);
        }

        order.setTotalPrice(orderTotalPrice);

        final Address customerAddress = addressService.convertAddress(address);
        order.setDeliveryAddress(customerAddress);

        addressService.save(customerAddress);
        orderRepository.save(order);
    }

    private OrderEntry createOrderEntry(Order order, ProductSessionDto sessionProduct) {
        final Product product = productRepository.findById(sessionProduct.getId())
                .orElseThrow(EntityExistsException::new);

        final OrderEntry orderEntry = new OrderEntry();
        orderEntry.setProduct(product);
        orderEntry.setQuantity(sessionProduct.getQuantity());
        orderEntry.setOrder(order);
        return orderEntry;
    }

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
