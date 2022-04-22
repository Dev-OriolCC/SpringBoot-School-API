package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.model.SchoolClass;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SchoolClassRepository;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonService personService;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    PersonRepository personRepository;


    @RequestMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());//

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson", person);
        //throw new RuntimeException("An error occurred..........");
        return "dashboard";
    }

    // Show register person form
    @RequestMapping(value = "/register")
    public String showRegisterForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    // Make a new student
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

    // Return the view of the logged in users profile with information
    @RequestMapping("/profile")
    public ModelAndView displayProfile(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        Person person1 = new Person();
        person1.setName(person.getName());
        person1.setMobileNumber(person.getMobileNumber());
        person1.setEmail(person.getEmail());
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            //person1 = getAddress1(person.getAddress().getAddress1());
            /*
            person1.setAddress1(person.getAddress().getAddress1());
            person1.setAddress2(person.getAddress().getAddress2());
            person1.setCity(person.getAddress().getCity());
            person1.setState(person.getAddress().getState());
            person1.setZipCode(person.getAddress().getZipCode());
            */
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("person", person1);
        return modelAndView;
    }

    // Return view with all the classes
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("schoolClasses", schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClass());
        return modelAndView;
    }

    // Store a new class
    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayClasses");
        return modelAndView;
    }

    // Delete Class
    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        for(Person person : schoolClass.get().getPersons()) {
           person.setSchoolClass(null);
           personRepository.save(person);
        }
        schoolClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayClasses");
        return modelAndView;
    }

    // Display students with the course selected..
    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId) {
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        modelAndView.addObject("schoolClass", schoolClass.get());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }
}
