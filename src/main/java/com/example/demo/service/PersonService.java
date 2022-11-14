package com.example.demo.service;

import com.example.demo.dto.requestDto.PersonRequestDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    public PersonResponseDto addPerson(PersonRequestDto personRequestDto);
    public List<PersonResponseDto> getPersons();
    public PersonResponseDto getPersonById(Integer personId);
    public Person getPerson(Integer personId);
    public PersonResponseDto deletePerson(Integer personId);
    public PersonResponseDto editPerson(Integer personId, PersonRequestDto personRequestDto);
    // Roles Section
    public PersonResponseDto addRoleToPerson(Integer personId, Integer roleId);
    public PersonResponseDto deleteRoleFromPerson(Integer personId);
    // Courses Section
    public PersonResponseDto addCourseToPerson(Integer courseId, Integer personId);
    // Address Section
    public PersonResponseDto addAddressToPerson(Integer personId, Integer addressId);
    public PersonResponseDto deleteAddressFromPerson(Integer personId);
    // SchoolClass Section
    public PersonResponseDto addSchoolClassToPerson(Integer personId, Integer schoolClassId);
    public PersonResponseDto deleteSchoolClassFromPerson(Integer personId);

}
