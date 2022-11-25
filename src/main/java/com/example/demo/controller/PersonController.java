package com.example.demo.controller;

import com.example.demo.dto.requestDto.PersonRequestDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody final PersonRequestDto personRequestDto) {
        PersonResponseDto personResponseDto = personService.addPerson(personRequestDto);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonResponseDto>> getPersons() {
        List<PersonResponseDto> personResponseDtos = personService.getPersons();
        return new ResponseEntity<>(personResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable final Integer id) {
        PersonResponseDto personResponseDto = personService.getPersonById(id);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonResponseDto> deletePerson(@PathVariable final Integer id) {
        PersonResponseDto personResponseDto = personService.deletePerson(id);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> editPerson(@PathVariable final Integer id,
                                                        @RequestBody final PersonRequestDto personRequestDto) {
        PersonResponseDto personResponseDto = personService.editPerson(id, personRequestDto);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addRole/{roleId}/to/{personId}")
    public ResponseEntity<PersonResponseDto> addRoleToPerson(@PathVariable final Integer roleId,
                                                             @PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.addRoleToPerson(roleId, personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<PersonResponseDto> deleteRoleFromPerson(@PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.deleteRoleFromPerson(personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @PutMapping("/addCourse/{courseId}/to/{personId}")
    public ResponseEntity<PersonResponseDto> addCourse(@PathVariable final Integer courseId,
                                                       @PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.addCourseToPerson(courseId, personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }
    //
    @PostMapping("/addAddress/{personId}/to/{addressId}")
    public ResponseEntity<PersonResponseDto> addAddress(@PathVariable final Integer personId,
                                                        @PathVariable final Integer addressId) {
        PersonResponseDto personResponseDto = personService.addAddressToPerson(personId, addressId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{personId}")
    public ResponseEntity<PersonResponseDto> deleteAddressFromPerson(@PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.deleteAddressFromPerson(personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @PutMapping("/addSchoolClass/{schoolClassId}/to/{personId}")
    public ResponseEntity<PersonResponseDto> addSchoolClassToPerson(@PathVariable final Integer schoolClassId,
                                                                    @PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.addSchoolClassToPerson(schoolClassId, personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSchoolClass/{personId}")
    public ResponseEntity<PersonResponseDto> deleteSchoolClassFromPerson(@PathVariable final Integer personId) {
        PersonResponseDto personResponseDto = personService.deleteSchoolClassFromPerson(personId);
        return new ResponseEntity<>(personResponseDto, HttpStatus.OK);
    }

}
