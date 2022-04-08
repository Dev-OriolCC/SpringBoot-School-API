package com.example.demo.controller;


import com.example.demo.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @GetMapping("/holidays")
    public String showHolidays(Model model) {
        List<Holiday> holidays = Arrays.asList(
                new Holiday("Jan 1", "New years eve", Holiday.Type.FESTIVAL),
                new Holiday("Jan 6", "Halloween", Holiday.Type.FEDERAL),
                new Holiday("Jan 10", "Christmas", Holiday.Type.FEDERAL),
                new Holiday("Jan 30", "Today", Holiday.Type.FESTIVAL)
        );
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).
                            collect(Collectors.toList())));
        }

        return "holidays";
    }

}
