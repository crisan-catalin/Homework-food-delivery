package com.example.fooddelivery.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.fooddelivery.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);
}
