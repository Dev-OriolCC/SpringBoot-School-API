package com.example.demo.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HolidayRequestDto {
    private String day;
    private String reason;
//    private EnumT type;

}
