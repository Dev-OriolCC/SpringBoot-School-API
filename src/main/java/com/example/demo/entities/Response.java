package com.example.demo.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String statusCode;
    private String statusMessage;

    private List body;

}
