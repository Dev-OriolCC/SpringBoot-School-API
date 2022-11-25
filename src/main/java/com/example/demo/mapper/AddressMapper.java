package com.example.demo.mapper;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.dto.responseDto.AddressResponseDto;
import com.example.demo.entities.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {
    public static AddressResponseDto AddressToAddressResponseDto(Address address) {
        return AddressResponseDto.builder()
                .id(address.getAddressId())
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

    public static List<AddressResponseDto> addressToAddressResponseDtos(List<Address> addressList) {
        List<AddressResponseDto> addressResponseDtos = new ArrayList<>();
        for (Address address: addressList) {
            addressResponseDtos.add(AddressToAddressResponseDto(address));
        }
        return addressResponseDtos;
    }

    public static Address addressRequestDtoToAddress(AddressRequestDto addressRequestDto) {
        return Address.builder()
                .address1(addressRequestDto.getAddress1())
                .address2(addressRequestDto.getAddress2())
                .city(addressRequestDto.getCity())
                .state(addressRequestDto.getState())
                .build();
    }

}
