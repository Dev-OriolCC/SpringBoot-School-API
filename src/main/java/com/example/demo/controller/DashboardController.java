package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SchoolClassRepository;
import com.example.demo.service.ContactService;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
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
    // [Pending_Auth]
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
    // [Pending]
    @RequestMapping(value = "/register")
    public String showRegisterForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }

    // 3- Create a new person (student)
    // Working tested
    @PostMapping("/createUser")
    public ResponseEntity<Response> createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        Response response = new Response();
        if (errors.hasErrors()) {
            response.setBody(errors.getFieldErrors());
            response.setStatusCode("400");
            response.setStatusMessage("Student could not be created");
            return failedResponse(response);
        }
        personService.createNewPerson(person);
        response.setStatusCode("200");
        response.setStatusMessage("Student created successfully!");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("studentCreated", "true")
                .body(response);
    }
    /*
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
    */

    // 4- Return the view of the logged-in user profile with information
    // [Pending_Auth]
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
    // Working tested
    @GetMapping("/displayClasses")
    public ResponseEntity<Response> displayClasses() {
        Response response = new Response();
        List<SchoolClass> SchoolClasses = schoolClassRepository.findAll();
        if(SchoolClasses.isEmpty()){
            return failedResponse(response);
        }
        response.setStatusCode("200");
        response.setStatusMessage("Classes fetched successfully");
        response.setBody(SchoolClasses);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isFound", "true")
                .body(response);
    }
    /*
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<SchoolClass> schoolClasses = schoolClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("schoolClasses", schoolClasses);
        modelAndView.addObject("schoolClass", new SchoolClass());
        return modelAndView;
    }
    */

    // 6- Store a new class
    // Working tested
    @PostMapping("/addNewClass")
    public ResponseEntity<Response> addNewClass(@Valid @PathVariable("schoolClass") SchoolClass schoolClass, Errors errors) {
        Response response = new Response();
        if(errors.hasErrors()) {
            response.setBody(errors.getFieldErrors());
            response.setStatusCode("400");
            response.setStatusMessage("Class NOT created");
            return failedResponse(response);
        }
        schoolClassRepository.save(schoolClass);
        response.setStatusCode("200");
        response.setStatusMessage("Class created successfully");
        //response.setBody(contactRepository.findById(contact.getContactId()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isCreated", "true")
                .body(response);

    }
    /*
    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("schoolClass") SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/displayClasses");
        return modelAndView;
    }
    */

    // 7- Delete Class
    // Working tested
    @DeleteMapping("/deleteClass/{id}")
    public ResponseEntity<Response> deleteClass(@PathVariable Integer id) {
        Response response = new Response();
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        if(schoolClass.isEmpty()) {
            response.setStatusMessage("400");
            response.setStatusMessage("Class NOT deleted");
            return failedResponse(response);
        }
        for(Person person: schoolClass.get().getPersons()) {
           person.setSchoolClass(null);
           personRepository.save(person);
        }
        schoolClassRepository.deleteById(id);
        response.setStatusMessage("200");
        response.setStatusMessage("Class deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("isDeleted", "true")
                .body(response);
    }
    /*
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
     */

    // 8- Display students with the course selected..
    // Working tested
    @GetMapping("/displayStudents/{id}")
    public ResponseEntity<Response> displayStudents(@PathVariable Integer id) {
        Response response = new Response();
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(id);
        List<Person> students = personRepository.getBySchoolClass(schoolClass.get());

        if(students.isEmpty()) {
            response.setStatusCode("400");
            response.setStatusMessage("Students not found in this class");
            return failedResponse(response);
        }
        response.setBody(students);
        response.setStatusCode("200");
        response.setStatusMessage("Students Found in this class");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("studentsFound", "true")
                .body(response);
    }
    /*
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
     */

    // 9- Add student with assigned class
    /*
    * Make it work without validations
    * */
//    @RequestMapping(value = "/addStudent",
//            produces = "application/json",
//            method=RequestMethod.PUT)
    @PatchMapping(value = "/addStudent")
    // Pending -> Check how to update relationship Data
    public ResponseEntity<Response> addStudent(@RequestPart(required = true) String email,
                                               @RequestPart(required = true) Integer classId) {
        Response response = new Response();

        Person person = personRepository.readByEmail(email);
        SchoolClass schoolClass = schoolClassRepository.getById(classId);
        //Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        if (person == null || schoolClass.getClassId() < 0) {
            response.setStatusMessage("Student not found/course");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header("isUptaded", "false")
                    .body(response);
        }
        // Assign class to student
        //SchoolClass schoolClass1 = schoolClass.getClass();
        //1
        person.setSchoolClass(schoolClass);
        //person.setSchoolClass(schoolClass);
        personRepository.save(person);
        //2
//        schoolClass.getPersons().add(person);
//        schoolClassRepository.save(schoolClass);
        // return response :D
        response.setStatusMessage("Student updated!");
        response.setStatusCode("200");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("studentsAdded", "true")
                .body(response);
    }
    /*
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
     */

    // 10- Delete a particular person (student)
    // Pending -> Check how to update relationship Data
    @GetMapping("/deleteStudent")
    public ResponseEntity<Response> deleteStudent(@RequestParam int personId) {
        Response response = new Response();
        Optional<Person> person = personRepository.findById(personId);
        person.get().setSchoolClass(null);
        personRepository.save(person.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("isSaved", "true")
                .body(response);
    }

    /*
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
     */

    // 11- Display all the courses from the db.
    @GetMapping("/displayCourses")
    public ResponseEntity<Response> displayCourses() {
        Response response = new Response();
        List<Courses> courses = coursesRepository.findAll();
        if (courses.isEmpty()) {
            response.setStatusCode("400");
            response.setStatusMessage("Error while fetching courses");
            return failedResponse(response);
        }
        response.setBody(courses);
        response.setStatusCode("200");
        response.setStatusMessage("Courses Found Successfully!");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("status", "true")
                .body(response);
    }
    /*
    public ModelAndView displayCourses(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        modelAndView.addObject("courses", courses);
        model.addAttribute("course", new Courses());
        return modelAndView;
    }
     */

    // 12- Add a new course to the db
    @PostMapping("/addNewCourse")
    public ResponseEntity<Response> addNewCourse(@Valid @ModelAttribute("courses") Courses courses, Errors errors) {
        //@PathVariable("schoolClass") SchoolClass schoolClass
        Response response = new Response();
        if(errors.hasErrors()) {
            response.setBody(errors.getFieldErrors());
            response.setStatusCode("400");
            response.setStatusMessage("Course NOT created...");
            return failedResponse(response);
        }
        coursesRepository.save(courses);
        response.setStatusMessage("200");
        response.setStatusMessage("Course created successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("status", "true")
                .body(response);
    }
    /*
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/displayCourses");
        return modelAndView;
    }
     */

    //13- Show students in the current course
    @GetMapping("/viewStudents")
    public ResponseEntity<Response> viewStudents(@RequestParam int id) {
        Response response = new Response();
        Optional<Courses> courses = coursesRepository.findById(id);
        if (courses.isEmpty()){
            response.setStatusMessage("Course not found!");
            return failedResponse(response);
        }
        List<Person> students = personRepository.findAllByCourses(courses);
        response.setBody(students);
        response.setStatusMessage("Students found");
        response.setStatusCode("200");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("status", "true")
                .body(response);
    }
    /*
    public ModelAndView viewStudents(Model model, @RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("course_students");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }
     */

    /*
    * METHOD TO RETURN FAILED RESPONSE
    * */
    public ResponseEntity<Response> failedResponse(Response response) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("statusRequest", "false")
                .body(response);
    }

}
