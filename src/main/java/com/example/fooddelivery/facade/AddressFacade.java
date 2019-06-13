package com.example.fooddelivery.facade;

import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;

/**
 * Used for general address operations.
 */
public interface AddressFacade {

    /**
     * Convert the addressForm to Address
     *
     * @param addressForm the {@link AddressForm}
     * @return the {@link Address}
     */
    Address convertToAddress(AddressForm addressForm);
}
