package com.example.demo.service;

import com.example.demo.constants.schoolprojectconstants;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean sendMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(schoolprojectconstants.OPEN);
        contact.setCreatedBy(schoolprojectconstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        // Save data to database
        Contact sentContact = contactRepository.save(contact);
        if(null != sentContact && sentContact.getContactId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    //2
    public List<Contact> findMessageWithOpenStatus() {
        List<Contact> contactMessages = contactRepository.findByStatus(schoolprojectconstants.OPEN);
        return contactMessages;
    }

    //3
    public boolean updateMessageStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(schoolprojectconstants.CLOSE);
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if (null != updatedContact && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
