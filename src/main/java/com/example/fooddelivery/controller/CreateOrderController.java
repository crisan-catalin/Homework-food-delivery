package com.example.fooddelivery.controller;

import com.example.fooddelivery.constants.Session;
import com.example.fooddelivery.constants.Views;
import com.example.fooddelivery.dto.ProductSessionDto;
import com.example.fooddelivery.dto.ProductWithQuantityDto;
import com.example.fooddelivery.dto.SessionUserDto;
import com.example.fooddelivery.facade.CartFacade;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.facade.RestaurantFacade;
import com.example.fooddelivery.forms.AddressForm;
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
    private static final String ORDER_ID = "orderId";
    private static final String ORDERS_LIST = "ordersList";
    private static final String ORDER_DETAILS = "orderDetails";
    private static final String SESSION_PRODUCTS = "sessionProducts";
    private static final String SESSION_ADDRESS = "sessionAddress";
    private static final String REDIRECT = "redirect:/";

    @Autowired
    private RestaurantFacade restaurantFacade;

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private OrderFacade orderFacade;

    @GetMapping()
    public String createOrder(Model model) {
        model.addAttribute(ADDRESS_FORM, new AddressForm());
        model.addAttribute(ADDED_PRODUCTS, new ArrayList<ProductWithQuantityDto>());
        model.addAttribute(RESTAURANTS, restaurantFacade.getRestaurantsNameAndId());

        return CREATE_ORDER_PAGE;
    }

    @GetMapping("/details/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId, Model model) {
        try {
            model.addAttribute(ORDER_DETAILS, orderFacade.getOrderDetails(Long.parseLong(orderId)));
            model.addAttribute(ORDER_ID, orderId);
            return ORDER_DETAILS_PAGE;
        } catch (Exception e) {
            return ERROR_PAGE;
        }
    }

    @GetMapping("/list")
    public String getOrdersList(Model model) {
        model.addAttribute(ORDERS_LIST, orderFacade.getOpenOrders());
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
        if (session.getAttribute(Session.USER) == null) {
            return REDIRECT + LOGIN_PAGE;
        }

        try {
            final List<ProductSessionDto> products = (List<ProductSessionDto>) session.getAttribute(SESSION_PRODUCTS);
            final SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Session.USER);
            orderFacade.createOrder(sessionUserDto.getId(), products, addressForm);
            session.removeAttribute(SESSION_PRODUCTS);

            return REDIRECT + "order/order-placed";
        } catch (Exception e) {
            return ERROR_PAGE;
        }
    }

    @PostMapping("/process")
    public String processOrder(@RequestParam(name = ORDER_ID) String orderId, HttpSession session) {
        try {
            final SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute(Session.USER);
            orderFacade.startOrderProcessing(sessionUserDto.getId(), Long.parseLong(orderId));
            return REDIRECT;
        } catch (Exception e) {
            return ERROR_PAGE;
        }
    }
}
