package com.example.demo.service;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.dto.responseDto.AddressResponseDto;
import com.example.demo.entities.Address;
import com.example.demo.mapper.AddressMapper;
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
    public List<AddressResponseDto> displayAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return AddressMapper.addressToAddressResponseDtos(addresses);
    }

    @Override
    public AddressResponseDto addAddress(AddressRequestDto addressRequestDto) {
        Address address = AddressMapper.addressRequestDtoToAddress(addressRequestDto);
        addressRepository.save(address);
        return AddressMapper.AddressToAddressResponseDto(address);
    }

}
