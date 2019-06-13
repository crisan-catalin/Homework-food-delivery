package com.example.fooddelivery.facade.impl;

import com.example.fooddelivery.facade.AddressFacade;
import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;

public class DefaultAddressFacade implements AddressFacade {

    @Override
    public Address convertToAddress(AddressForm addressForm) {
        Address address = new Address();
        address.setCity(addressForm.getCity());
        address.setStreet(addressForm.getStreet());
        address.setNumber(addressForm.getNumber());

        return address;
    }
}
