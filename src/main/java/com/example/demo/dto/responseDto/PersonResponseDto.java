package com.example.demo.dto.responseDto;

import com.example.demo.entities.Address;
import com.example.demo.entities.Courses;
import com.example.demo.entities.Roles;
import com.example.demo.entities.SchoolClass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonResponseDto {
    // private int personId;
    private String name;
    private String mobileNumber;
    private String email;
    // private String confirmEmail;
    // private String pwd;
    // private String confirmPwd;
    //
    //  private Integer roleId;
    // private Integer addressId;
    // private Integer schoolClassId;

    // === Relational Data ===
    // New save object ER rather than ID pointer.
    private Roles roles;
    private Set<Courses> courses;
    private Address address;
    private SchoolClass schoolClass;
    // === =============== ===
}
