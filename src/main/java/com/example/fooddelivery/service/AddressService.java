package com.example.fooddelivery.service;

import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;

/**
 * AddressService for address operations.
 */
public interface AddressService {

    /**
     * Save address via AddressRepository
     *
     * @param address the address
     * @return saved entity
     */
    Address save(Address address);
}
