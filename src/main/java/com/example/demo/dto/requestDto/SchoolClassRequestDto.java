package com.example.demo.dto.requestDto;

import com.example.demo.entities.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SchoolClassRequestDto {

    private String name;
    private Set<Person> persons;

}
