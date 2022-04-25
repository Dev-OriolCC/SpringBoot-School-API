package com.example.demo.controller;

import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import com.example.demo.model.SchoolClass;
import com.example.demo.repository.CoursesRepository;
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

    @Autowired
    CoursesRepository coursesRepository;


    // 1- Show Dashboard with auth user information
    @RequestMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());//

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson", person);
        //throw new RuntimeException("An error occurred..........");
        return "dashboard";
    }

    // 2- Show register person form
    @RequestMapping(value = "/register")
    public String showRegisterForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    // 3- Create a new person (student)
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

    // 4- Return the view of the logged in users profile with information
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

    // 5- Return view with all the classes
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("schoolClasses", schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClass());
        return modelAndView;
    }

    // 6- Store a new class
    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayClasses");
        return modelAndView;
    }

    // 7- Delete Class
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

    // 8- Display students with the course selected..
    @RequestMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        modelAndView.addObject("schoolClass", schoolClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("schoolClass", schoolClass.get());
        if (error != null) {
            errorMessage = "Invalid email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    // 9- Add student with assigned class
    /*
    * Make it work without validations
    * */
    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/displayStudents?classId="+schoolClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setSchoolClass(schoolClass);
        personRepository.save(personEntity);
        schoolClass.getPersons().add(personEntity);
        schoolClassRepository.save(schoolClass);
        modelAndView.setViewName("redirect:/displayStudents?classId="+schoolClass.getClassId());
        return modelAndView;
    }

    // 10- Delete a particular person (student)
    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        SchoolClass schoolClass = (SchoolClass) session.getAttribute("schoolClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setSchoolClass(null);
        schoolClass.getPersons().remove(person.get());
        SchoolClass schoolClassSaved = schoolClassRepository.save(schoolClass);
        session.setAttribute("eazyClass",schoolClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+schoolClass.getClassId());
        return modelAndView;
    }

    // 11- Display all the courses from the db.
    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        modelAndView.addObject("courses", courses);
        model.addAttribute("course", new Courses());
        return modelAndView;
    }

    // 12- Add a new course to the db
    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/displayCourses");
        return modelAndView;
    }

    //13- Show students in the current course
    @GetMapping("viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("course_students");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

}
