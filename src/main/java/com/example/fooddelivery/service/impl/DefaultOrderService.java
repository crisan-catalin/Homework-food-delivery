package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.dto.OrderListDto;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderListDto> getOpenOrders() {
        List<Order> orders = orderRepository.findAllByDeliveryStatusOrderByIdDesc(DeliveryStatus.PLACED);

        return orders.stream()
                .map(order -> new OrderListDto(order.getId(), order.getCustomer().getName(), order.getTotalPrice(),
                        new AddressDto(order.getDeliveryAddress().getCity(), order.getDeliveryAddress().getStreet(), order.getDeliveryAddress().getNumber())))
                .collect(Collectors.toList());
    }
}
