package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
