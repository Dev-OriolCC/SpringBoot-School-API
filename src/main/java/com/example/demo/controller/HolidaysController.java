package com.example.demo.controller;


import com.example.demo.model.Holiday;
import com.example.demo.model.Response;
import com.example.demo.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class HolidaysController {

    @Autowired
    private HolidayRepository holidayRepository;

    // Working tested
    @GetMapping("/holidays/{display}")
    public ResponseEntity<Response> showHolidays(@PathVariable String display) {
        Response response = new Response();
        // Iterable<Holiday> holidays = holidayRepository.findAll();
        //List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false).toList();
        if (display == null || display.equals("all")) {
            List<Holiday> holidays = holidayRepository.findAll();
            response.setBody(holidays);
        } else if(display.equals("festival")) {
            List<Holiday> holidays = holidayRepository.findAllByType(Holiday.Type.FESTIVAL);
            response.setBody(holidays);
        } else { //if(display.equals("federal"))
            List<Holiday> holidays = holidayRepository.findAllByType(Holiday.Type.FEDERAL);
            response.setBody(holidays);
        }
        response.setStatusCode("200");
        response.setStatusMessage("Holidays fetched successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("holidayFetched", "true")
                .body(response);

    }

    /*
    @GetMapping("/holidays/{display}")
    public String showHolidays(@PathVariable String display, Model model) {
        if (null != display && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival", true);
        } else if (display != null && display.equals("federal")) {
            model.addAttribute("federal", true);
        }
        Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false)
                .collect(Collectors.toList());

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).
                            collect(Collectors.toList())));
        }

        return "holidays";
    }
    */

}
