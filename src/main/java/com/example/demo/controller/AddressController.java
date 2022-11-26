package com.example.demo.controller;

import com.example.demo.dto.requestDto.AddressRequestDto;
import com.example.demo.dto.responseDto.AddressResponseDto;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressResponseDto>> getAddresses() {
        List<AddressResponseDto> addressList = addressService.displayAddresses();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AddressResponseDto> addAddress(@RequestBody final AddressRequestDto addressRequestDto) {
        AddressResponseDto address = addressService.addAddress(addressRequestDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
