package com.example.fooddelivery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fooddelivery.constants.Views;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.impl.DefaultUserFacade;
import com.example.fooddelivery.forms.LogInUserForm;

@Controller
@RequestMapping
public class MainController {

    private static final String SESSION_USER = "sessionUser";
    private static final String LOG_IN_USER_FORM = "logInUserForm";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Autowired
    DefaultUserFacade defaultUserFacade;

    @GetMapping("/")
    public String getHomePage() {
        return Views.MAIN_PAGE;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model, HttpSession session) {

        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(SESSION_USER);
        if (sessionUserDto != null) {
            return Views.MAIN_PAGE;
        } else {
            model.addAttribute(LOG_IN_USER_FORM, new LogInUserForm());
            return Views.LOGIN_PAGE;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logInUser(@ModelAttribute(LOG_IN_USER_FORM) LogInUserForm logInUserForm, Model model, HttpSession session) {
        SessionUserDto sessionUserDto = defaultUserFacade.logInUser(logInUserForm);
        if (sessionUserDto != null) {
            session.setAttribute(SESSION_USER, sessionUserDto);
            return Views.MAIN_PAGE;
        } else {
            model.addAttribute(ERROR_MESSAGE, "Incorrect combination of email and password");
            return Views.LOGIN_PAGE;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogoutPage(HttpSession session) {
        session.removeAttribute(SESSION_USER);
        return "redirect:/";
    }
}
