package com.example.demo.service;

import com.example.demo.dto.requestDto.CoursesRequestDto;
import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.model.Courses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoursesService {

    public List<CoursesResponseDto> displayCourses();
    public CoursesResponseDto addCourse(CoursesRequestDto coursesRequestDto);
    public CoursesResponseDto deleteCourse(Integer courseId);
    public Courses getCourse(Integer courseId);
    public CoursesResponseDto getCourseById(Integer courseId);

}
