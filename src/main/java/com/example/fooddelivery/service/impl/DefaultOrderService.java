package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.builder.impl.DefaultOrderBuilder;
import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.exceptions.EntityNotFoundException;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderEntry;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.UserRepository;
import com.example.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createOrder(User customer, Address customerAddress, Set<OrderEntry> entries) {
        final Order order = new DefaultOrderBuilder()
                .setCustomer(customer)
                .setDeliveryAddress(customerAddress)
                .setEntries(entries)
                .build();

        addressRepository.save(customerAddress);
        orderRepository.save(order);
    }

    public void startOrderProcessing(Long livratorId, Long orderId) throws EntityNotFoundException {
        Optional<User> livrator = userRepository.findById(livratorId);
        Optional<Order> order = orderRepository.findById(orderId);
        if (livrator.isPresent() && order.isPresent()) {
            Order givenOrder = order.get();
            givenOrder.setDeliveryStatus(DeliveryStatus.IN_PROGRESS);
            givenOrder.setLivrator(livrator.get());
            orderRepository.save(givenOrder);
            return;
        }
        throw new EntityNotFoundException();
    }

    @Override
    public List<Order> getOpenOrders() {
        return orderRepository.findAllByDeliveryStatusOrderByIdDesc(DeliveryStatus.PLACED);
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Map<String, Set<OrderEntry>> getOrderEntriesGroupedByRestaurantName(Set<OrderEntry> orderEntries) {
        return orderEntries.stream()
                .collect(Collectors.groupingBy(orderEntry -> orderEntry.getProduct().getRestaurant().getName(), Collectors.toSet()));
    }
  
    @Override
    public List<Order> getOrdersByUserId(Long id) {
        return orderRepository.getOrdersByCustomerId(id);
    }

    @Override
    public List<Order> getOrdersByLivratorId(Long id) {
        return orderRepository.getOrdersByLivratorId(id);
    }
}
