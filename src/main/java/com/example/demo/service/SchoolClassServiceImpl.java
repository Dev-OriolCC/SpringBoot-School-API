package com.example.demo.service;

import com.example.demo.dto.mapper;
import com.example.demo.dto.requestDto.SchoolClassRequestDto;
import com.example.demo.dto.responseDto.SchoolClassResponseDto;
import com.example.demo.model.SchoolClass;
import com.example.demo.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    @Autowired
    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<SchoolClassResponseDto> getSchoolClasses() {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        return mapper.schoolClassToSchoolClassResponseDtos(schoolClasses);
    }

    @Override
    public SchoolClassResponseDto addNewClass(SchoolClassRequestDto schoolClassRequestDto) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName(schoolClassRequestDto.getName());
        schoolClass.setPersons(schoolClassRequestDto.getPersons());
        schoolClassRepository.save(schoolClass);
        return mapper.schoolClassToSchoolClassResponseDto(schoolClass);
    }

    @Override
    public SchoolClassResponseDto deleteSchooClass(Integer schoolClassId) {
        SchoolClass schoolClass = getSchoolClass(schoolClassId);
        schoolClassRepository.delete(schoolClass);
        return mapper.schoolClassToSchoolClassResponseDto(schoolClass);

    }

    @Override
    public SchoolClass getSchoolClass(Integer schoolClassId) {
        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId).orElseThrow(()->
                new IllegalArgumentException("Class not found!"+schoolClassId));
        return schoolClass;
    }


}
