package com.example.demo.dto.requestDto;

import com.example.demo.entities.Courses;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class PersonRequestDto {
    private String name;
    private String mobileNumber;
    private String email;
    private String confirmEmail;
    private String pwd;
    private String confirmPwd;
    //
    private Integer roleId;
    private Integer addressId;
    private Integer schoolClassId;
    private Set<Courses> courses;
}
