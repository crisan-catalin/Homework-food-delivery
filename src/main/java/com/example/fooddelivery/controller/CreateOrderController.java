package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.forms.OrderForm;
import com.example.fooddelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.example.fooddelivery.constants.Views.CREATE_ORDER_PAGE;

@Controller
@RequestMapping("/order")
public class CreateOrderController {

    private static final String ORDER_FORM = "orderForm";
    private static final String RESTAURANTS = "restaurants";
    private static final String ADDED_PRODUCTS = "addedProducts";
    private static final String SESSION_PRODUCTS = "sessionProducts";

    @Autowired
    RestaurantService restaurantService;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ORDER_FORM, new OrderForm());
        model.addAttribute(ADDED_PRODUCTS, new ArrayList<ProductWithQuantityDto>());
        model.addAttribute(RESTAURANTS, restaurantService.getRestaurantsName());

        return CREATE_ORDER_PAGE;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addProducts")
    public void addProductsToCart(@RequestBody List<ProductWithQuantityDto> products, HttpSession session) {
        session.setAttribute(SESSION_PRODUCTS, products);
    }

}