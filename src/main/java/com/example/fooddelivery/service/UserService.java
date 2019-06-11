package com.example.fooddelivery.service;

import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.UserRepository;

/**
 * UserService for user operations.
 */
public interface UserService {

    /**
     * Get user from DB
     *
     * @param email user email
     * @param password user password
     * @return {@link User} entity
     */
    User getUserByEmailAndPassword(String email, String password);

    /**
     * Save user via {@link UserRepository}
     *
     * @param user entity
     * @return saved entity
     */
    User save(User user);
}
