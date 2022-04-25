package com.example.demo.controller;

import com.example.demo.constants.schoolprojectconstants;
import com.example.demo.model.Contact;
import com.example.demo.model.Response;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
//@Controller
@RestController
public class ContactController {
    // Create log to display results
    // @Slf4j --> Creates the logic to log information
    // private static Logger log = LoggerFactory.getLogger(ContactService.class);
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    ContactRepository contactRepository;

    // Check how to return the HTML view and that's it
    @RequestMapping(value = "/contact")
    public String showContactPage(Model model) {

        model.addAttribute("contact", new Contact());
        return "contact";
    }

    /*
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.info(errors.toString());
            return "contact";
        }
        // Go to service layer to make business logic....
        contactService.sendMessageDetails(contact);
        return "redirect:/contact";
    }
    */
    // Working tested
    @PostMapping("/sendMessage")
    public ResponseEntity<Response> sendMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setBody(errors.getFieldErrors());
            response.setStatusCode("400");
            response.setStatusMessage("Contact NOT sent");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("contactSent", "false")
                    .body(response);
        }
        contactService.sendMessageDetails(contact);
        response.setStatusCode("200");
        response.setStatusMessage("Contact sent successfully");
        //response.setBody(contactRepository.findById(contact.getContactId()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("contactSent", "true")
                .body(response);

    }

    // Working Tested!
    @RequestMapping("/displayMessages")
    public ResponseEntity<Response> displayMessages() {
        List<Contact> contactMessages = contactService.findMessageWithOpenStatus();
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Contacts fetched successfully");
        response.setBody(contactMessages);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isFound", "true")
                .body(response);
    }

    // Working Tested!
    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contact) {
        Response response = new Response();
        Optional<Contact> contact_optional = contactRepository.findById(contact.getContactId());
        if(contact_optional.isPresent()) {
            //contactService.updateMessageStatus(contact.getContactId());
            contact_optional.get().setStatus(schoolprojectconstants.CLOSE);
            contactRepository.save(contact_optional.get());
        } else {
            response.setStatusCode("400");
            response.setStatusMessage("Contact NOT updated");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("contactUpdated", "false")
                    .body(response);
        }

        response.setStatusCode("200");
        response.setStatusMessage("Contact status updated");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("contactUpdated", "true")
                .body(response);
        //return "redirect:/displayMessages";
    }

//    @RequestMapping(value = "/closeMsg",method = GET)
//    public String closeMsg(@RequestParam int id) {
//        contactService.updateMessageStatus(id);
//        return "redirect:/displayMessages";
//    }

}
