package com.example.demo.dto.responseDto;

import com.example.demo.model.Address;
import com.example.demo.model.Courses;
import com.example.demo.model.Roles;
import com.example.demo.model.SchoolClass;
import lombok.Data;

import javax.management.relation.Role;
import java.util.Set;

@Data
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
