package com.example.fooddelivery.controller;

import com.example.fooddelivery.constants.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-placed")
public class OrderPlacedController {

    @GetMapping
    public String getView() {
        return Views.ORDER_PLACED_PAGE;
    }
}
