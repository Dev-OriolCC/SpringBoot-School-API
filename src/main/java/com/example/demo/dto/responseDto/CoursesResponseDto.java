package com.example.demo.dto.responseDto;

import com.example.demo.model.Person;
import lombok.Data;

import java.util.Set;

@Data
public class CoursesResponseDto {
    // private Integer courseId;
    private String name;
    private String fees;
    // --> Number of persons
    private Integer students;
    //private Set<Person> persons; --OLD

}
