package com.example.demo.dto.responseDto;

import com.example.demo.model.Person;
import lombok.Data;

import java.util.Set;

@Data
public class SchoolClassResponseDto {
    private Integer classId;
    private String name;
    private Set<Person> persons;
}
