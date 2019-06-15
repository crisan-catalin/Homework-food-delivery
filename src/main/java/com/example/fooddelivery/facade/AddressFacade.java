package com.example.fooddelivery.facade;

import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.dto.AddressDto;

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

    /**
     * Convert the Address to AddressDto
     *
     * @param address the {@link Address}
     * @return the {@link AddressDto}
     */
    AddressDto convertToAddressDto(Address address);
}
