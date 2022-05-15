package com.example.demo.service;


import com.example.demo.dto.requestDto.SchoolClassRequestDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.model.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolClassService {

    public List<SchoolClassResponseDto> getSchoolClasses();
    public SchoolClassResponseDto addNewClass(SchoolClassRequestDto schoolClassRequestDto);
    public SchoolClassResponseDto deleteSchooClass(Integer schoolClassId);
    public SchoolClass getSchoolClass(Integer schoolClassId);

}
