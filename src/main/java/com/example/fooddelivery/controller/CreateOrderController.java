package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.facade.impl.DefaultCartFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.service.impl.DefaultRestaurantService;
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

    private static final String ADDRESS_FORM = "addressForm";
    private static final String RESTAURANTS = "restaurants";
    private static final String ADDED_PRODUCTS = "addedProducts";
    private static final String SESSION_PRODUCTS = "sessionProducts";
    private static final String SESSION_ADDRESS = "sessionAddress";
    private static final String REDIRECT = "redirect:/";

    @Autowired
    private DefaultRestaurantService defaultRestaurantService;

    @Autowired
    private DefaultCartFacade defaultCartFacade;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ADDRESS_FORM, new AddressForm());
        model.addAttribute(ADDED_PRODUCTS, new ArrayList<ProductWithQuantityDto>());
        model.addAttribute(RESTAURANTS, defaultRestaurantService.getRestaurantsNameAndId());

        return CREATE_ORDER_PAGE;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/updateAddress")
    public void updateOrderAddress(@RequestBody AddressForm address, HttpSession session) {
        session.setAttribute(SESSION_ADDRESS, address);
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

    @PostMapping("/create")
    public String submit(@ModelAttribute(ADDRESS_FORM) AddressForm addressForm, HttpSession session) {
        List<ProductSessionDto> products = (List<ProductSessionDto>) session.getAttribute(SESSION_PRODUCTS);

        defaultCartFacade.createOrder(products, addressForm);
        session.removeAttribute(SESSION_PRODUCTS);

        return REDIRECT + "order-placed";
    }
}
