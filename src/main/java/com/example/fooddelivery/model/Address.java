package com.example.fooddelivery.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @NotNull
    @Column
    private String city;

    @NotNull
    @Column
    private String street;

    @NotNull
    @Column
    private Integer number;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Restaurant> restaurants;
}