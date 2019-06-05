package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.OrderDetailsDto;
import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.*;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.ProductRepository;
import com.example.fooddelivery.repository.UserRepository;
import com.example.fooddelivery.service.AddressService;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class DefaultOrderFacade implements OrderFacade {

    private static final String ORDER_NOT_FOUND = "Order not found";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressService addressService;

    @Override
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
    public OrderDetailsDto getOrderDetails(Long orderId) throws EntityNotFoundException {
        Optional<Order> order = orderService.getOrder(orderId);
        if (order.isPresent()) {
            Order customerOrder = order.get();
            OrderDetailsDto orderDetails = new OrderDetailsDto();
            orderDetails.setCustomerDetails(orderService.getContactDetails(customerOrder));
            orderDetails.setTotalPrice(customerOrder.getTotalPrice());
            orderDetails.setOrderEntriesByRestaurant(orderService.getOrderEntriesByRestaurantDtos(customerOrder.getOrderEntries()));
            return orderDetails;
        }

        throw new EntityNotFoundException(ORDER_NOT_FOUND);
    }
}
