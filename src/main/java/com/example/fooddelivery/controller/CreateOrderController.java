package com.example.fooddelivery.controller;

import com.example.fooddelivery.forms.OrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.fooddelivery.constants.Views.CREATE_ORDER_PAGE;

@Controller
@RequestMapping("/order")
public class CreateOrderController {

    @GetMapping("/")
    public String createOrder(Model model) {
        model.addAttribute("orderForm", new OrderForm());

        return CREATE_ORDER_PAGE;
    }

    @PostMapping("/create")
    public String submit(@ModelAttribute("orderForm") OrderForm order) {

        return CREATE_ORDER_PAGE;
    }
}
