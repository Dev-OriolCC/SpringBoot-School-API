package com.example.demo.dto.requestDto;

import com.example.demo.model.Holiday;
import lombok.Data;

import javax.persistence.EnumType;

@Data
public class HolidayRequestDto {
    private String day;
    private String reason;
//    private EnumT type;

}
