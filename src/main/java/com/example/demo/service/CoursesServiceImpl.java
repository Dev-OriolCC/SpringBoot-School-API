package com.example.demo.service;

import com.example.demo.dto.mapper;
import com.example.demo.dto.requestDto.CoursesRequestDto;
import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.model.Courses;
import com.example.demo.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository coursesRepository;
    @Autowired
    public CoursesServiceImpl(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<CoursesResponseDto> displayCourses() {
        List<Courses> courses = coursesRepository.findAll();
        return mapper.coursesToCoursesResponseDtos(courses);
    }

    @Override
    public CoursesResponseDto addCourse(CoursesRequestDto coursesRequestDto) {
        Courses courses = new Courses();
        courses.setName(coursesRequestDto.getName());
        courses.setFees(coursesRequestDto.getFees());
        coursesRepository.save(courses);
        return mapper.coursesToCoursesResponseDto(courses);
    }

    @Override
    public CoursesResponseDto deleteCourse(Integer courseId) {
        Courses courses = getCourse(courseId);
        coursesRepository.delete(courses);
        return mapper.coursesToCoursesResponseDto(courses);
    }

    @Override
    public Courses getCourse(Integer courseId) {
        Courses courses = coursesRepository.findById(courseId).orElseThrow(() ->
                new IllegalArgumentException("Course not found"+courseId));
        return courses;
    }

    @Override
    public CoursesResponseDto getCourseById(Integer courseId) {
        return mapper.coursesToCoursesResponseDto(getCourse(courseId));
    }
}
