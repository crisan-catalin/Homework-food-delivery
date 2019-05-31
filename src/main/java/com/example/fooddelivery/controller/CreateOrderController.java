package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.facade.impl.DefaultCartFacade;
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
import java.util.stream.Collectors;

import static com.example.fooddelivery.constants.Views.CREATE_ORDER_PAGE;

@Controller
@RequestMapping("/order")
public class CreateOrderController {

    private static final String ORDER_FORM = "orderForm";
    private static final String RESTAURANTS = "restaurants";
    private static final String ADDED_PRODUCTS = "addedProducts";
    private static final String SESSION_PRODUCTS = "sessionProducts";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DefaultCartFacade defaultCartFacade;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ORDER_FORM, new OrderForm());
        model.addAttribute(ADDED_PRODUCTS, new ArrayList<ProductWithQuantityDto>());
        model.addAttribute(RESTAURANTS, restaurantService.getRestaurantsName());

        return CREATE_ORDER_PAGE;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/addProducts")
    public void addProductsToCart(@RequestBody List<ProductSessionDto> products, HttpSession session) {
        List<ProductSessionDto> sessionProducts = (List<ProductSessionDto>) session.getAttribute(SESSION_PRODUCTS);

        if (sessionProducts == null) {
            session.setAttribute(SESSION_PRODUCTS, products);
            return;
        }

        List<ProductSessionDto> mergedProducts = defaultCartFacade.mergeWithExistingProducts(sessionProducts, products);
        session.setAttribute(SESSION_PRODUCTS, mergedProducts);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/removeProduct")
    public void deleteProductFromCart(@RequestBody ProductSessionDto product, HttpSession session) {
        List<ProductSessionDto> products = (List<ProductSessionDto>) session.getAttribute(SESSION_PRODUCTS);
        List<ProductSessionDto> productsAfterRemoving = products.stream()
                .filter(p -> !p.getId().equals(product.getId()))
                .collect(Collectors.toList());

        session.setAttribute(SESSION_PRODUCTS, productsAfterRemoving);
    }

}
