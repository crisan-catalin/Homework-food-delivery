package com.example.fooddelivery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fooddelivery.constants.Views;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.impl.DefaultOrderFacade;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private static final String SESSION_USER = "sessionUser";
    private static final String ORDERS = "orders";

    @Autowired
    private DefaultOrderFacade defaultOrderFacade;

    @GetMapping()
    public String getOrders(Model model, HttpSession session) {
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(SESSION_USER);
        if (sessionUserDto != null) {
            model.addAttribute(ORDERS, defaultOrderFacade.getOrders(sessionUserDto));
            return Views.ORDERS_PAGE;
        } else {
            return Views.MAIN_PAGE;
        }
    }

    @RequestMapping(value = "/pending")
    public String getPendingOrders(Model model, HttpSession session) {
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(SESSION_USER);
        if (sessionUserDto != null) {
            model.addAttribute(ORDERS, defaultOrderFacade.getPendingOrders(sessionUserDto));
            return Views.ORDERS_PAGE;
        } else {
            return Views.MAIN_PAGE;
        }
    }

    @RequestMapping(value = "/taken")
    public String getTakenOrders(Model model, HttpSession session) {
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(SESSION_USER);
        if (sessionUserDto != null) {
            model.addAttribute(ORDERS, defaultOrderFacade.getTakenOrders(sessionUserDto));
            return Views.ORDERS_PAGE;
        } else {
            return Views.MAIN_PAGE;
        }
    }

    @RequestMapping(value = "/order-placed")
    public String getView() {
        return Views.ORDER_PLACED_PAGE;
    }
}
