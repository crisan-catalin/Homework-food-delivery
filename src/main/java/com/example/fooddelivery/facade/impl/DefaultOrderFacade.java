package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.*;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.facade.AddressFacade;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.service.OrderService;
import com.example.fooddelivery.service.ProductService;
import com.example.fooddelivery.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultOrderFacade implements OrderFacade {

    private static final String ORDER_NOT_FOUND = "Order not found";

    @Autowired
    AddressFacade addressFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public void startOrderProcessing(Long livratorId, Long orderId) throws EntityNotFoundException {
        orderService.startOrderProcessing(livratorId, orderId);
    }

    @Override
    public List<OrderListDto> getOpenOrders() {
        return orderService.getOpenOrders().stream()
            .map(order -> new OrderListDto(order.getId(), order.getCustomer().getName(), order.getTotalPrice(),
                new AddressDto(order.getDeliveryAddress().getCity(),
                    order.getDeliveryAddress().getStreet(),
                    order.getDeliveryAddress().getNumber())))
            .collect(Collectors.toList());
    }

    @Override
    public void createOrder(Long userId, List<ProductSessionDto> products, AddressForm address) throws EntityNotFoundException {
        final User customer = userService.getUserById(userId);
        final Set<OrderEntry> orderEntries = new HashSet<>(products.size());

        for (ProductSessionDto product : products) {
            orderEntries.add(productService.generateOrderEntry(product.getId(), product.getQuantity()));
        }

        orderService.createOrder(customer, addressFacade.convertToAddress(address), orderEntries);
    }

    @Override
    public OrderDetailsDto getOrderDetails(Long orderId) throws EntityNotFoundException {
        final Optional<Order> order = orderService.getOrder(orderId);
        if (order.isPresent()) {
            Order customerOrder = order.get();
            OrderDetailsDto orderDetails = new OrderDetailsDto();
            orderDetails.setOrderAvailable(DeliveryStatus.PLACED.equals(customerOrder.getDeliveryStatus()));
            orderDetails.setCustomerDetails(getContactDetails(customerOrder));
            orderDetails.setTotalPrice(customerOrder.getTotalPrice());
            orderDetails.setOrderEntriesByRestaurant(getOrderEntriesByRestaurantDtos(customerOrder.getOrderEntries()));
            return orderDetails;
        }

        throw new EntityNotFoundException(ORDER_NOT_FOUND);
    }

    private ContactDetailsDto getContactDetails(Order order) {
        User user = order.getCustomer();
        Address deliveryAddress = order.getDeliveryAddress();
        AddressDto userAddressDto = new AddressDto(deliveryAddress.getCity(), deliveryAddress.getStreet(), deliveryAddress.getNumber());
        return new ContactDetailsDto(user.getName(), user.getPhone(), userAddressDto);
    }

    private Set<OrderEntriesByRestaurantDto> getOrderEntriesByRestaurantDtos(Set<OrderEntry> orderEntries) {
        Map<String, Set<OrderEntry>> orderEntriesGroupedByRestaurantName = orderService.getOrderEntriesGroupedByRestaurantName(orderEntries);
        Set<OrderEntriesByRestaurantDto> orderEntriesByRestaurantDtos = new HashSet<>(orderEntriesGroupedByRestaurantName.size());

        for (Map.Entry<String, Set<OrderEntry>> entry : orderEntriesGroupedByRestaurantName.entrySet()) {
            OrderEntriesByRestaurantDto orderEntriesByRestaurant = new OrderEntriesByRestaurantDto();
            orderEntriesByRestaurant.setRestaurantName(entry.getKey());
            orderEntriesByRestaurant.setProducts(
                entry.getValue().stream()
                    .map(orderEntry -> new ProductWithQuantityDto(orderEntry.getId(),
                        orderEntry.getProduct().getName(),
                        orderEntry.getProduct().getPrice(),
                        orderEntry.getQuantity()))
                    .collect(Collectors.toSet())
            );
            orderEntriesByRestaurantDtos.add(orderEntriesByRestaurant);
        }

        return orderEntriesByRestaurantDtos;
    }

    @Override
    public List<OrderDto> getOrders(SessionUserDto user) {
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        return orders.stream().map(this::convertToOrderDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getPendingOrders(SessionUserDto user) {
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        return orders.stream()
            .filter(order -> order.getDeliveryStatus().equals(DeliveryStatus.PLACED))
            .map(this::convertToOrderDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTakenOrders(SessionUserDto user) {
        List<Order> orders = orderService.getOrdersByLivratorId(user.getId());
        return orders.stream().map(this::convertToOrderDto).collect(Collectors.toList());
    }

    private OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDeliveryStatus(order.getDeliveryStatus());
        orderDto.setDeliveryAddress(order.getDeliveryAddress());
        orderDto.setOrderEntries(order.getOrderEntries());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setCustomer(order.getCustomer());
        orderDto.setLivrator(order.getLivrator());
        return orderDto;
    }
}
