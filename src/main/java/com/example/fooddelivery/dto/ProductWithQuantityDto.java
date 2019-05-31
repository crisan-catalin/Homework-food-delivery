package com.example.fooddelivery.dto;

public class ProductWithQuantityDto {
    private Long id;
    private String name;
    private Long price;
    private Integer quantity;

    public ProductWithQuantityDto(Long id, String name, Long price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
