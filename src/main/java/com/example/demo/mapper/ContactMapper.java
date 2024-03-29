package com.example.demo.mapper;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.dto.responseDto.ContactResponseDto;
import com.example.demo.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactMapper {

    public static ContactResponseDto contactToContactResponseDto(Contact contact) {
        return ContactResponseDto.builder()
                .id(contact.getContactId())
                .name(contact.getName())
                .mobileNum(contact.getMobileNum())
                .email(contact.getEmail())
                .subject(contact.getSubject())
                .message(contact.getMessage())
                .status(contact.getStatus())
                .build();
    }

    public static List<ContactResponseDto> contactToContactResponseDtoList(List<Contact> contacts) {
        List<ContactResponseDto> contactResponseDtoList = new ArrayList<>();
        for(Contact contact: contacts) {
            contactResponseDtoList.add(contactToContactResponseDto(contact));
        }
        return contactResponseDtoList;
    }

    public static Contact contactRequestDtoToContact(ContactRequestDto contactRequestDto) {
        return Contact.builder()
                .name(contactRequestDto.getName())
                .mobileNum(contactRequestDto.getMobileNum())
                .email(contactRequestDto.getEmail())
                .subject(contactRequestDto.getSubject())
                .message(contactRequestDto.getMessage())
                .status(contactRequestDto.getStatus())
                .build();
    }

}
