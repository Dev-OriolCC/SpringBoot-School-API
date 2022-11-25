package com.example.demo.controller;

import com.example.demo.dto.requestDto.CoursesRequestDto;
import com.example.demo.dto.responseDto.CoursesResponseDto;
import com.example.demo.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses")
public class CoursesController {
    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }


    @GetMapping("/")
    public ResponseEntity<List<CoursesResponseDto>> displayCourses() {
        List<CoursesResponseDto> coursesResponseDtos = coursesService.displayCourses();
        return new ResponseEntity<>(coursesResponseDtos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CoursesResponseDto> addCourse(@RequestBody final CoursesRequestDto coursesRequestDto) {
        CoursesResponseDto coursesResponseDto = coursesService.addCourse(coursesRequestDto);
            return new ResponseEntity<>(coursesResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CoursesResponseDto> deleteCourse(@PathVariable final Integer id) {
        CoursesResponseDto coursesResponseDto = coursesService.deleteCourse(id);
        return new ResponseEntity<>(coursesResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoursesResponseDto> getCourseById(@PathVariable final Integer id) {
        CoursesResponseDto coursesResponseDto = coursesService.getCourseById(id);
        return new ResponseEntity<>(coursesResponseDto, HttpStatus.OK);
    }

}
