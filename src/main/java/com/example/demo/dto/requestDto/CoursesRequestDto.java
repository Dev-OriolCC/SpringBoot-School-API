package com.example.demo.dto.requestDto;

import com.example.demo.model.Person;
import lombok.Data;

import java.util.Set;


@Data
public class CoursesRequestDto {

    private String name;
    private String fees;
    private Set<Person> persons;
}
