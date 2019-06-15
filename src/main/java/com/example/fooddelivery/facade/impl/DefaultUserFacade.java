package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.AddressFacade;
import com.example.fooddelivery.facade.UserFacade;
import com.example.fooddelivery.forms.LogInUserForm;
import com.example.fooddelivery.forms.RegisterForm;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.service.AddressService;
import com.example.fooddelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultUserFacade implements UserFacade {

    @Autowired
    private AddressFacade addressFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Override
    public SessionUserDto logInUser(LogInUserForm logInUserForm) {
        User user = userService.getUserByEmailAndPassword(logInUserForm.getEmail(), logInUserForm.getPassword());
        if (user != null) {
            return convertToSessionUser(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean registerUser(RegisterForm registerForm) {
        User savedUser = userService.save(convertRegisterFormToUser(registerForm));
        return savedUser != null;
    }

    private SessionUserDto convertToSessionUser(User user) {
        SessionUserDto sessionUserDto = new SessionUserDto();
        sessionUserDto.setId(user.getId());
        sessionUserDto.setName(user.getName());
        return sessionUserDto;
    }

    private User convertRegisterFormToUser(RegisterForm registerForm) {
        User user = new User();
        user.setName(registerForm.getName());
        user.setPhone(registerForm.getPhone());
        user.setEmail(registerForm.getEmail());
        user.setPassword(registerForm.getPassword());
        user.setAddress(addressService.save(addressFacade.convertToAddress(registerForm.getAddress())));
        return user;
    }
}
