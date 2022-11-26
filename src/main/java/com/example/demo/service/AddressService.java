package com.example.demo.service;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.dto.responseDto.AddressResponseDto;
import com.example.demo.entities.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<AddressResponseDto> displayAddresses();
    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto);

}
