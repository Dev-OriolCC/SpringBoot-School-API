package com.example.demo.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponseDto {
    private int id;
    private String address1;
    private String address2;
    private String city;
    private String state;
}
