package com.example.demo.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoursesResponseDto {
    private Integer id;
    private String name;
    private String fees;
    private Integer students;
    //private Set<Person> persons; --OLD

}
