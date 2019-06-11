package com.example.fooddelivery;

import com.example.fooddelivery.facade.CartFacade;
import com.example.fooddelivery.facade.OrderFacade;
import com.example.fooddelivery.facade.impl.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public CartFacade cartFacade() {
        return new DefaultCartFacade();
    }

    @Bean
    public OrderFacade orderFacade() {
        return new DefaultOrderFacade();
    }

    @Bean
    public DefaultUserFacade userFacade() {
        return new DefaultUserFacade();
    }

    @Bean
    public DefaultRestaurantFacade restaurantFacade() {
        return new DefaultRestaurantFacade();
    }

    @Bean
    public DefaultProductFacade productFacade() {
        return new DefaultProductFacade();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
