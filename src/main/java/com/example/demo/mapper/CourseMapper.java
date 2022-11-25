package com.example.demo.mapper;

import com.example.demo.dto.requestDto.CoursesRequestDto;
import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.entities.Courses;

public class CourseMapper {

    public static CoursesResponseDto coursesToCoursesResponseDto(Courses courses) {
        return CoursesResponseDto.builder()
                .id(courses.getCourseId())
                .name(courses.getName())
                .fees(courses.getFees())
                .students(courses.getPersons().size())
                .build();
    }

    // public static List<CoursesResponseDto>
    public static Courses coursesRequestDtoToCourses(CoursesRequestDto coursesRequestDto) {
        return Courses.builder()
                .name(coursesRequestDto.getName())
                .fees(coursesRequestDto.getFees())
                .build();
    }

}
