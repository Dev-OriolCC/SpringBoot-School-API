package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ContactController {
    // Create log to display results
    // @Slf4j --> Creates the logic to log information
    // private static Logger log = LoggerFactory.getLogger(ContactService.class);
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact")
    public String showContactPage() {
        return "contact";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public ModelAndView sendMessage(Contact contact) {
        // Go to service layer to make business logic....
        contactService.sendMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }

}