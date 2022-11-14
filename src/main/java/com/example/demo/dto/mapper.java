package com.example.demo.dto;

import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.entities.Courses;
import com.example.demo.entities.Person;
import com.example.demo.entities.SchoolClass;

import java.util.ArrayList;
import java.util.List;

public class mapper {

    public static PersonResponseDto personToPersonResponseDto(Person person) {
        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setName(person.getName());
        personResponseDto.setMobileNumber(person.getMobileNumber());
        personResponseDto.setEmail(person.getEmail());
        // === Relational Data ===
        personResponseDto.setRoles(person.getRoles());
        personResponseDto.setAddress(person.getAddress());
        personResponseDto.setCourses(person.getCourses()); // 
        personResponseDto.setSchoolClass(person.getSchoolClass());
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
        coursesResponseDto.setName(courses.getName());
        coursesResponseDto.setFees(courses.getFees());
        // - Students
        coursesResponseDto.setStudents(courses.getPersons().size());

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
        schoolClassResponseDto.setName(schoolClass.getName());
        schoolClassResponseDto.setStudents(schoolClass.getPersons().size());
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
