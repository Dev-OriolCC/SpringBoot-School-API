package com.example.demo.mapper;

import com.example.demo.dto.requestDto.PersonRequestDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.entities.Person;
import com.example.demo.entities.Roles;
import com.example.demo.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonMapper {

//    private final RolesRepository rolesRepository;
//    @Autowired
//    public PersonMapper(RolesRepository rolesRepository) {
//        this.rolesRepository = rolesRepository;
//    }

    public static PersonResponseDto personToPersonResponseDto(Person person) {
        return PersonResponseDto.builder()
                .id(person.getPersonId())
                .name(person.getName())
                .mobileNumber(person.getMobileNumber())
                .email(person.getEmail())
                .roles(person.getRoles())
                .build();
    }

    public static Person personRequestDtoToPerson(PersonRequestDto personRequestDto) {
        //Roles roles = rolesRepository.findById(1);
        return Person.builder()
                .name(personRequestDto.getName())
                .mobileNumber(personRequestDto.getMobileNumber())
                .email(personRequestDto.getEmail())
                .confirmEmail(personRequestDto.getConfirmEmail())
                .pwd(personRequestDto.getPwd())
                .confirmPwd(personRequestDto.getConfirmPwd())
                //.roles()
                .build();
    }

}
