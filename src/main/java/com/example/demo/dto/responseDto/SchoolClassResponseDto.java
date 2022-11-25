package com.example.demo.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SchoolClassResponseDto {
    // private Integer classId;
    private String name;
    //private Set<Person> persons;
    // --> Number of persons
    private Integer students;
}
