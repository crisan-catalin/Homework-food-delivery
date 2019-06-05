package com.example.fooddelivery.repository;

import com.example.fooddelivery.enums.DeliveryStatus;
import com.example.fooddelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByDeliveryStatusOrderByIdDesc(DeliveryStatus deliveryStatus);
}
