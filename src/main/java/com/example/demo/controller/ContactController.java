package com.example.demo.controller;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.dto.responseDto.ContactResponseDto;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactResponseDto>> getContactMessages() {
        List<ContactResponseDto> contactMessagesList = contactService.displayContactMessages();
        return new ResponseEntity<>(contactMessagesList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ContactResponseDto> addContactMessage(@RequestBody final ContactRequestDto contactRequestDto) {
        ContactResponseDto contact = contactService.addContactMessages(contactRequestDto);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> editContactMessage(@PathVariable final Integer id) {
        ContactResponseDto contact = contactService.editContactMessage(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactResponseDto> deleteContactMessage(@PathVariable final  Integer id) {
        ContactResponseDto contact = contactService.deleteContactMessage(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

}
