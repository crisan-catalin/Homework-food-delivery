package com.example.fooddelivery.controller;

import com.example.fooddelivery.constants.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String getHomePage() {
        return Views.MAIN_PAGE;
    }
}
