package com.example.fooddelivery.controller;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fooddelivery.constants.Views;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.impl.DefaultUserFacade;
import com.example.fooddelivery.forms.LogInUserForm;
import com.example.fooddelivery.forms.RegisterForm;

@Controller
@RequestMapping
public class MainController {

    private static final String SESSION_USER = "sessionUser";
    private static final String LOG_IN_USER_FORM = "logInUserForm";
    private static final String REGISTER_FORM = "registerForm";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String INFO_MESSAGE = "infoMessage";
    private static final String REDIRECT = "redirect:/";

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
    public String logInUser(@NotNull @ModelAttribute(LOG_IN_USER_FORM) LogInUserForm logInUserForm, Model model, HttpSession session) {
        SessionUserDto sessionUserDto = defaultUserFacade.logInUser(logInUserForm);
        if (sessionUserDto != null) {
            session.setAttribute(SESSION_USER, sessionUserDto);
            return Views.MAIN_PAGE;
        } else {
            model.addAttribute(ERROR_MESSAGE, "Incorrect combination of email and password.");
            return Views.LOGIN_PAGE;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogoutPage(HttpSession session) {
        session.removeAttribute(SESSION_USER);
        return REDIRECT;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute(REGISTER_FORM, new RegisterForm());
        return Views.REGISTER_PAGE;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@NotNull @ModelAttribute(REGISTER_FORM) RegisterForm registerForm, Model model, RedirectAttributes attributes) {
        if (defaultUserFacade.registerUser(registerForm)) {
            attributes.addFlashAttribute(INFO_MESSAGE, "User created successfully! Please log in with email and password.");
            return REDIRECT + "login";
        } else {
            model.addAttribute(ERROR_MESSAGE, "User couldn't be created. Please try again.");
            return Views.REGISTER_PAGE;
        }
    }
}
