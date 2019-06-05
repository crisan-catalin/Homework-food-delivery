package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.dto.*;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void updateOrderStatus(Long orderId, DeliveryStatus status) throws EntityNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order givenOrder = order.get();
            givenOrder.setDeliveryStatus(status);
            orderRepository.save(givenOrder);
            return;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<OrderListDto> getOpenOrders() {
        List<Order> orders = orderRepository.findAllByDeliveryStatusOrderByIdDesc(DeliveryStatus.PLACED);

        return orders.stream()
                .map(order -> new OrderListDto(order.getId(), order.getCustomer().getName(), order.getTotalPrice(),
                        new AddressDto(order.getDeliveryAddress().getCity(), order.getDeliveryAddress().getStreet(), order.getDeliveryAddress().getNumber())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public ContactDetailsDto getContactDetails(Order order) {
        User user = order.getCustomer();
        Address deliveryAddress = order.getDeliveryAddress();
        AddressDto userAddressDto = new AddressDto(deliveryAddress.getCity(), deliveryAddress.getStreet(), deliveryAddress.getNumber());
        return new ContactDetailsDto(user.getName(), user.getPhone(), userAddressDto);
    }

    @Override
    public Set<OrderEntriesByRestaurantDto> getOrderEntriesByRestaurantDtos(Set<OrderEntry> orderEntries) {
        Map<String, Set<OrderEntry>> orderEntriesGroupedByRestaurantName = getOrderEntriesGroupedByRestaurantName(orderEntries);
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

    private Map<String, Set<OrderEntry>> getOrderEntriesGroupedByRestaurantName(Set<OrderEntry> orderEntries) {
        return orderEntries.stream()
                .collect(Collectors.groupingBy(orderEntry -> orderEntry.getProduct().getRestaurant().getName(), Collectors.toSet()));
    }
}
