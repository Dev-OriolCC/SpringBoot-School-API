package com.example.demo.dto.requestDto;

import lombok.Data;

@Data
public class ContactRequestDto {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private Boolean status;

}
