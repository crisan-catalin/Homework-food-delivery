package com.example.fooddelivery.service.impl;

import com.example.fooddelivery.forms.AddressForm;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultAddressService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address convertAddress(AddressForm addressForm) {
        Address address = new Address();
        address.setCity(addressForm.getCity());
        address.setStreet(addressForm.getStreet());
        address.setNumber(addressForm.getNumber());

        return address;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
