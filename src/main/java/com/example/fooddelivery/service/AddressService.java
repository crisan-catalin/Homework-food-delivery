package com.example.fooddelivery.service;

import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;

/**
 * AddressService for address operations.
 */
public interface AddressService {

    /**
     * Convert {@link AddressForm} to {@link Address}
     *
     * @param addressForm form model
     * @return address entity
     */
    Address convertAddress(AddressForm addressForm);

    /**
     * Save address via AddressRepository
     *
     * @param address entity
     * @return saved entity
     */
    Address save(Address address);
}
