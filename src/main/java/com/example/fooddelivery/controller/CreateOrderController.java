package com.example.fooddelivery.controller;

import com.example.fooddelivery.forms.OrderForm;
import com.example.fooddelivery.repository.RestaurantRepository;
import com.example.fooddelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String ORDER_FORM = "orderForm";
    private static final String RESTAURANTS = "restaurants";

    @Autowired
    RestaurantService restaurantService;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ORDER_FORM, new OrderForm());
        model.addAttribute(RESTAURANTS, restaurantService.getRestaurantsName());

        return CREATE_ORDER_PAGE;
    }
}
