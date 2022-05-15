package com.example.demo.dto.responseDto;

import com.example.demo.model.Address;
import com.example.demo.model.Courses;
import com.example.demo.model.SchoolClass;
import lombok.Data;

import java.util.Set;

@Data
public class PersonResponseDto {
    private int personId;
    private String name;
    private String mobileNumber;
    private String email;
    private String confirmEmail;
    private String pwd;
    private String confirmPwd;
    //
    private Integer roleId;
    //
    private Integer addressId;
    private Integer schoolClassId;
    private Set<Courses> courses;
    private Address address;
    // New form rather than ID
    private SchoolClass schoolClass;

}
