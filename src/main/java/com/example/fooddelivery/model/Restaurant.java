package com.example.fooddelivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @NotNull
    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private Address address;
}