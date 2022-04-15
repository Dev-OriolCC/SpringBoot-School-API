package com.example.demo.service;

import com.example.demo.constants.schoolprojectconstants;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean sendMessageDetails(Contact contact) {
        boolean isSaved = true;
        contact.setStatus(schoolprojectconstants.OPEN);
        contact.setCreatedBy(schoolprojectconstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        // Save data to database
        int result = contactRepository.sendContactMessage(contact);
        if(result >0) {
            isSaved = true;
        }
        return isSaved;
    }
}
