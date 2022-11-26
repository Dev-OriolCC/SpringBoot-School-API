package com.example.demo.mapper;

import com.example.demo.dto.requestDto.SchoolClassRequestDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.entities.SchoolClass;

public class SchoolClassMapper {

    public static SchoolClassResponseDto schoolClassToSchoolClassResponseDto(SchoolClass schoolClass) {
        return SchoolClassResponseDto.builder()
                .name(schoolClass.getName())
                .students(schoolClass.getPersons().size())
                .build();
    }

    public static SchoolClass schoolClassRequestDtoToSchoolClass(SchoolClassRequestDto schoolClassRequestDto) {
        return SchoolClass.builder()
                .name(schoolClassRequestDto.getName())
                .persons(schoolClassRequestDto.getPersons()).build();
    }

}
