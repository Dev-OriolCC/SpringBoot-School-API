package com.example.demo.service;

import com.example.demo.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    public boolean sendMessageDetails(Contact contact) {
        boolean isSaved = true;
        // Save data to database
        log.info(contact.toString());
        log.info("TEST");
        return isSaved;
    }
}
