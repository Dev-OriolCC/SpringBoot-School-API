package com.example.demo.service;

import com.example.demo.constants.schoolprojectconstants;
import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public boolean sendMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(schoolprojectconstants.OPEN);
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
    public void updateMessageStatus() {
//        if(contact.getContactId() > 0 && contactRepository.existsById(contact.getContactId())) {
//            contact.setStatus(schoolprojectconstants.CLOSE);
//            contactRepository.save(contact);
//        }


//        boolean isUpdated = false;
//        Optional<Contact> contact = contactRepository.findById(contactId);
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(schoolprojectconstants.CLOSE);
//        });
//        Contact updatedContact = contactRepository.save(contact.get());
//        if (null != updatedContact && updatedContact.getUpdatedBy() != null) {
//            isUpdated = true;
//        }
//        return isUpdated;

    }

}
