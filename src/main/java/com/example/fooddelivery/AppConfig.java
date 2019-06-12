package com.example.fooddelivery;

import com.example.fooddelivery.facade.impl.DefaultCartFacade;
import com.example.fooddelivery.facade.impl.DefaultOrderFacade;
import com.example.fooddelivery.facade.impl.DefaultUserFacade;

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
    public DefaultCartFacade cartFacade() {
        return new DefaultCartFacade();
    }

    @Bean
    public DefaultUserFacade userFacade() {
        return new DefaultUserFacade();
    }

    @Bean
    public DefaultOrderFacade orderFacade() {
        return new DefaultOrderFacade();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
    }
}
