package com.example.demo.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursesResponseDto {
    // private Integer courseId;
    private String name;
    private String fees;
    // --> Number of persons
    private Integer students;
    //private Set<Person> persons; --OLD

}
