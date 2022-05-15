package com.example.demo.service;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<Address> displayAddresses();
    public Address addAddress(AddressRequestDto addressRequestDto);

}
