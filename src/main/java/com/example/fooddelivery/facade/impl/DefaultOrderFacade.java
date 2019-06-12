package com.example.fooddelivery.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.fooddelivery.dto.OrderDto;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.service.OrderService;

public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    private OrderService orderService;

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
