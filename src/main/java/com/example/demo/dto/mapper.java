package com.example.demo.dto;

import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import com.example.demo.model.SchoolClass;

import java.util.ArrayList;
import java.util.List;

public class mapper {

    public static PersonResponseDto personToPersonResponseDto(Person person) {
        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setName(person.getName());
        personResponseDto.setMobileNumber(person.getMobileNumber());
        personResponseDto.setEmail(person.getEmail());
        personResponseDto.setConfirmEmail(person.getConfirmEmail());
        personResponseDto.setPwd(person.getPwd());
        personResponseDto.setConfirmPwd(person.getConfirmPwd());//
        //
        // Condition when Role is NULL
        if(person.getRoles() != null){
            personResponseDto.setRoleId(person.getRoles().getRoleId());
        }
//        if (person.getAddress() != null) {
//            personResponseDto.setAddressId(person.getAddress().getAddressId());
//        }
        personResponseDto.setAddress(person.getAddress());
        //personResponseDto.setSchoolClassId(person.getSchoolClass().getClassId());
        personResponseDto.setCourses(person.getCourses());
        //
        return personResponseDto;
    }

    public static List<PersonResponseDto> personToPersonResponseDtos(List<Person> persons) {
        List<PersonResponseDto> personResponseDtos = new ArrayList<>();
        for (Person person: persons) {
            personResponseDtos.add(personToPersonResponseDto(person));
        }
        return personResponseDtos;
    }

    //
    public static CoursesResponseDto coursesToCoursesResponseDto(Courses courses) {
        CoursesResponseDto coursesResponseDto = new CoursesResponseDto();
        coursesResponseDto.setCourseId(courses.getCourseId());
        coursesResponseDto.setName(courses.getName());
        coursesResponseDto.setFees(courses.getFees());
        coursesResponseDto.setPersons(courses.getPersons());
        return coursesResponseDto;
    }

    public static List<CoursesResponseDto> coursesToCoursesResponseDtos(List<Courses> courses) {
        List<CoursesResponseDto> coursesResponseDtos = new ArrayList<>();
        for (Courses courses1: courses) {
            coursesResponseDtos.add(coursesToCoursesResponseDto(courses1));
        }
        return coursesResponseDtos;
    }
    //
    public static SchoolClassResponseDto schoolClassToSchoolClassResponseDto(SchoolClass schoolClass) {
        SchoolClassResponseDto schoolClassResponseDto = new SchoolClassResponseDto();
        schoolClassResponseDto.setClassId(schoolClass.getClassId());
        schoolClassResponseDto.setName(schoolClass.getName());
        schoolClassResponseDto.setPersons(schoolClass.getPersons());
        return schoolClassResponseDto;
    }

    public static List<SchoolClassResponseDto> schoolClassToSchoolClassResponseDtos(List<SchoolClass> schoolClasses) {
        List<SchoolClassResponseDto> schoolClassResponseDtos = new ArrayList<>();
        for(SchoolClass schoolClass: schoolClasses) {
            schoolClassResponseDtos.add(schoolClassToSchoolClassResponseDto(schoolClass));
        }
        return schoolClassResponseDtos;
    }



}
