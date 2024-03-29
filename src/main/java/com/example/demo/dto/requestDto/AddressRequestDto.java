package com.example.demo.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressRequestDto {
    private String address1;
    private String address2;
    private String city;
    private String state;
}
    