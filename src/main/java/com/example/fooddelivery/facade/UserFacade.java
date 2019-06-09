package com.example.fooddelivery.facade;

import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.forms.LogInUserForm;

/**
 * UserFacade used for general user operations.
 */
public interface UserFacade {

    /**
     * Log in a user
     *
     * @param logInUserForm the log in form which contains two {@link String's } email and password
     * @return {@link SessionUserDto} if combination of email and password from form exists in DB
     */
    SessionUserDto logInUser(LogInUserForm logInUserForm);
}
