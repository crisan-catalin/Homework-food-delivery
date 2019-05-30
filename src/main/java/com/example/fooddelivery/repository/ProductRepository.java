package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByRestaurant_Id(Long id);
}
