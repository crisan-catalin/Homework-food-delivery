package com.example.fooddelivery.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private Integer phone;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String password;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "customer")
    private Set<Order> placedOrders;

    @OneToMany(mappedBy = "livrator")
    private Set<Order> takenOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Order> getPlacedOrders() {
        return placedOrders;
    }

    public void setPlacedOrders(Set<Order> placedOrders) {
        this.placedOrders = placedOrders;
    }

    public Set<Order> getTakenOrders() {
        return takenOrders;
    }

    public void setTakenOrders(Set<Order> takenOrders) {
        this.takenOrders = takenOrders;
    }
}