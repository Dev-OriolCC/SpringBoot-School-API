package com.example.demo.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponseDto {
    private int id;
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private Boolean status;

}
