package com.example.demo.service;

import com.example.demo.dto.requestDto.ContactRequestDto;
import com.example.demo.dto.responseDto.ContactResponseDto;
import com.example.demo.entities.Contact;
import com.example.demo.mapper.ContactMapper;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactResponseDto> displayContactMessages() {
        List<Contact> contacts = contactRepository.findAll();
        return ContactMapper.contactToContactResponseDtoList(contacts);
    }

    @Override
    public ContactResponseDto addContactMessages(ContactRequestDto contactRequestDto) {
        Contact contact = ContactMapper.contactRequestDtoToContact(contactRequestDto);
        contactRepository.save(contact);
        return ContactMapper.contactToContactResponseDto(contact);
    }

    @Override
    public ContactResponseDto editContactMessage(Integer contactId) {
        Contact contact = contactRepository.findById(contactId).get();
        contact.setStatus(!contact.getStatus());
        contactRepository.save(contact);
        return ContactMapper.contactToContactResponseDto(contact);
    }

    @Override
    public ContactResponseDto deleteContactMessage(Integer contactId) {
        Contact contact = contactRepository.findById(contactId).get();
        contactRepository.delete(contact);
        return ContactMapper.contactToContactResponseDto(contact);
    }


}
