package com.example.demo.service;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.dto.responseDto.ContactResponseDto;
import com.example.demo.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    public List<ContactResponseDto> displayContactMessages();
    public ContactResponseDto addContactMessages(ContactRequestDto contactRequestDto);
    public ContactResponseDto editContactMessage(Integer contactId);
    public ContactResponseDto deleteContactMessage(Integer contactId);

}
