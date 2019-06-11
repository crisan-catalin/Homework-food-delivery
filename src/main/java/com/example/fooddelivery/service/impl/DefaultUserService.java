package com.example.fooddelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.UserRepository;
import com.example.fooddelivery.service.UserService;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
