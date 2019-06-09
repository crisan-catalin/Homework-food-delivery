package com.example.fooddelivery.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.UserFacade;
import com.example.fooddelivery.forms.LogInUserForm;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.service.UserService;

public class DefaultUserFacade implements UserFacade {

    @Autowired
    UserService userService;

    @Override
    public SessionUserDto logInUser(LogInUserForm logInUserForm) {
        User user = userService.getUserByEmailAndPassword(logInUserForm.getEmail(), logInUserForm.getPassword());
        if (user != null) {
            return convert(user);
        } else {
            return null;
        }
    }

    private SessionUserDto convert(User user) {
        SessionUserDto sessionUserDto = new SessionUserDto();
        sessionUserDto.setId(user.getId());
        sessionUserDto.setName(user.getName());
        return sessionUserDto;
    }
}
