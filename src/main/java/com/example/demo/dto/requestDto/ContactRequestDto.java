package com.example.demo.dto.requestDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private Boolean status;

}
