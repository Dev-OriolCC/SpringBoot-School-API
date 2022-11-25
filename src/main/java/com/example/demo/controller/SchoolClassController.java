package com.example.demo.controller;

import com.example.demo.dto.requestDto.SchoolClassRequestDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.entities.SchoolClass;
import com.example.demo.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schoolclass")
public class SchoolClassController {
    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/")
    public ResponseEntity<List<SchoolClassResponseDto>> getSchoolClasses(){
        List<SchoolClassResponseDto> schoolClassList =  schoolClassService.getSchoolClasses();
        return new ResponseEntity<>(schoolClassList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SchoolClassResponseDto> addSchoolClass(@RequestBody final SchoolClassRequestDto schoolClassRequestDto) {
        SchoolClassResponseDto schoolClass = schoolClassService.addNewClass(schoolClassRequestDto);
        return new ResponseEntity<>(schoolClass, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SchoolClassResponseDto> deleteSchoolClass(@PathVariable final Integer id) {
        SchoolClassResponseDto schoolClassResponseDto = schoolClassService.deleteSchooClass(id);
        return new ResponseEntity<>(schoolClassResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getSchoolClass(@PathVariable final Integer id) {
        SchoolClass schoolClassResponse = schoolClassService.getSchoolClass(id);
        return new ResponseEntity<>(schoolClassResponse, HttpStatus.OK);

    }

}
