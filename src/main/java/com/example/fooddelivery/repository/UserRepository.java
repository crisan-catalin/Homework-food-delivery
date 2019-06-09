package com.example.fooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fooddelivery.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);
}
