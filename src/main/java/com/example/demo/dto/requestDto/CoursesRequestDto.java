package com.example.demo.dto.requestDto;

import com.example.demo.entities.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Builder
public class CoursesRequestDto {

    private String name;
    private String fees;
    private Set<Person> persons;
}
