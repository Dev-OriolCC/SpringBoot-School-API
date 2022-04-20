package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonService personService;


    @RequestMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        //throw new RuntimeException("An error occurred..........");
        return "dashboard";
    }

    // Show register person form
    @RequestMapping(value = "/register")
    public String showRegisterForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    //
    @RequestMapping(value ="/createUser",method = { RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if(errors.hasErrors()){
            return "register";
        }
        boolean isSaved = personService.createNewPerson(person);
        if(isSaved){
            return "redirect:/dashboard";
        }else {
            return "register";
        }
    }

}
