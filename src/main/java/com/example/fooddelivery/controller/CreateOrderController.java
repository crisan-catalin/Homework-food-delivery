package com.example.fooddelivery.controller;

import com.example.fooddelivery.constants.Views;
import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.facade.CartFacade;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.service.OrderService;
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

import static com.example.fooddelivery.constants.Views.*;

@Controller
@RequestMapping("/order")
public class CreateOrderController {

    private static final String ADDRESS_FORM = "addressForm";
    private static final String RESTAURANTS = "restaurants";
    private static final String ADDED_PRODUCTS = "addedProducts";
    private static final String ORDERS_LIST = "ordersList";
    private static final String ORDER_DETAILS = "orderDetails";
    private static final String SESSION_PRODUCTS = "sessionProducts";
    private static final String SESSION_ADDRESS = "sessionAddress";
    private static final String REDIRECT = "redirect:/";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFacade orderFacade;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ADDRESS_FORM, new AddressForm());
        model.addAttribute(ADDED_PRODUCTS, new ArrayList<ProductWithQuantityDto>());
        model.addAttribute(RESTAURANTS, restaurantService.getRestaurantsNameAndId());

        return CREATE_ORDER_PAGE;
    }

    @GetMapping("/details/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId, Model model) {
        try {
            model.addAttribute(ORDER_DETAILS, orderFacade.getOrderDetails(Long.parseLong(orderId)));
            return ORDER_DETAILS_PAGE;
        } catch (Exception e) {
            return ERROR_PAGE;
        }
    }

    @GetMapping("/list")
    public String getOrdersList(Model model) {
        model.addAttribute(ORDERS_LIST, orderService.getOpenOrders());
        return ORDERS_LIST_PAGE;
    }

    @GetMapping("/order-placed")
    public String getOrderPlaced() {
        return Views.ORDER_PLACED_PAGE;
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

        List<ProductSessionDto> mergedProducts = cartFacade.mergeWithExistingProducts(sessionProducts, products);
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

        orderFacade.createOrder(products, addressForm);
        session.removeAttribute(SESSION_PRODUCTS);

        return REDIRECT + "order/order-placed";
    }
}
