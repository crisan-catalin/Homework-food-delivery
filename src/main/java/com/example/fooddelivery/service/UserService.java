package com.example.fooddelivery.service;

import com.example.fooddelivery.model.User;

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
}
