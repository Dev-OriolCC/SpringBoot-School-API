package com.example.demo.dto.requestDto;

import com.example.demo.model.Person;
import lombok.Data;

import java.util.Set;

@Data
public class SchoolClassRequestDto {
    private String name;
    private Set<Person> persons;

}
