package com.example.demo.service;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.entities.Address;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> displayAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    @Override
    public Address addAddress(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setAddress1(addressRequestDto.getAddress1());
        address.setAddress2(addressRequestDto.getAddress2());
        address.setCity(addressRequestDto.getCity());
        address.setState(addressRequestDto.getState());
        addressRepository.save(address);
        return address;
    }

}
