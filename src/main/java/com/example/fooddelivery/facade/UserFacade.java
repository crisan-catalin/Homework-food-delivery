package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.forms.LogInUserForm;
import com.example.fooddelivery.forms.RegisterForm;

/**
 * UserFacade used for general user operations.
 */
public interface UserFacade {

    /**
     * Log in a user
     *
     * @param logInUserForm the log in form which contains two {@link String's } email and password
     * @return {@link SessionUserDto} if combination of email and password from form exists in DB, null otherwise
     */
    SessionUserDto logInUser(LogInUserForm logInUserForm);

    /**
     * Register a new user
     *
     * @param registerForm the register form which contains new user's information
     * @return {@link Boolean} true if user was registered successfully, false otherwise
     */
    boolean registerUser(RegisterForm registerForm);
}
