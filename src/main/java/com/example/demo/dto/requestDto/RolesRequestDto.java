package com.example.demo.dto.requestDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RolesRequestDto {
    private String roleName;
}
