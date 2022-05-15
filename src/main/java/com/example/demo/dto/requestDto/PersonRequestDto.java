package com.example.demo.dto.requestDto;

import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import lombok.Data;

import java.util.Set;

@Data
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
