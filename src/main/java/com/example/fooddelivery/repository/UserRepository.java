package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
