package com.example.demo.service;

import com.example.demo.dto.mapper;
import com.example.demo.dto.requestDto.PersonRequestDto;
import com.example.demo.dto.responseDto.PersonResponseDto;
import com.example.demo.entities.*;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository; // TEST
    private final CoursesService coursesService;
    private final CoursesRepository coursesRepository;
    private final AddressRepository addressRepository;
    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, RolesRepository rolesRepository, CoursesService coursesService, CoursesRepository coursesRepository, AddressRepository addressRepository, SchoolClassRepository schoolClassRepository) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
        this.coursesService = coursesService;
        this.coursesRepository = coursesRepository;
        this.addressRepository = addressRepository;
        this.schoolClassRepository = schoolClassRepository;
    }


    @Override
    public PersonResponseDto addPerson(PersonRequestDto personRequestDto) {
        Person person = PersonMapper.personRequestDtoToPerson(personRequestDto);
        Roles roles = rolesRepository.getById(1); //  Student
        person.setRoles(roles);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public List<PersonResponseDto> getPersons() {
        List<Person> persons = personRepository.findAll();
        return mapper.personToPersonResponseDtos(persons);
    }

    @Override
    public PersonResponseDto getPersonById(Integer personId) {
        Person person =  personRepository.findById(personId).get(); //getPerson(personId);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public Person getPerson(Integer personId) {
        Person person = personRepository.findById(personId).orElseThrow(() ->
                new IllegalArgumentException("Person not found with ID_"+personId));
        return person;
    }

    @Override
    public PersonResponseDto deletePerson(Integer personId) {
        Person person = getPerson(personId);
        personRepository.delete(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto editPerson(Integer personId, PersonRequestDto personRequestDto) {
        Person personToEdit = personRepository.findById(personId).get();
        // Test
        if(personRequestDto.getName() != null) {
            personToEdit.setName(personRequestDto.getName());
        }
        if(personRequestDto.getEmail() != null) {
            personToEdit.setEmail(personRequestDto.getEmail());
        }
        if(personRequestDto.getMobileNumber() != null) {
            personToEdit.setMobileNumber(personRequestDto.getMobileNumber());
        }
        personRepository.save(personToEdit);
        return mapper.personToPersonResponseDto(personToEdit);
    }

    @Transactional
    @Override
    public PersonResponseDto addRoleToPerson(Integer personId, Integer roleId) {
        Person person =  personRepository.findById(personId).get(); //getPerson(personId);
        if (Objects.nonNull(person.getRoles())) {
            throw new RuntimeException("Person has a role");
        }
        Roles role = rolesRepository.findById(roleId).get();
        person.setRoles(role);
        return mapper.personToPersonResponseDto(person);
    }

//    @Transactional
    @Override
    public PersonResponseDto deleteRoleFromPerson(Integer personId) {
        Person person = personRepository.findById(personId).get();
        person.setRoles(null);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    //@Transactional
    @Override
    public PersonResponseDto addCourseToPerson(Integer personId, Integer courseId) {
        Person person = personRepository.findById(courseId).get();
        Courses course =  coursesRepository.findById(personId).get(); //coursesService.getCourse(courseId);
        person.getCourses().add(course);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto addAddressToPerson(Integer personId, Integer addressId) {
        Person person = personRepository.findById(personId).get();
        Address address =  addressRepository.findById(addressId).get();
        if(Objects.nonNull(person.getAddress())){
            throw new RuntimeException("Person has already an address");
        }
        person.setAddress(address);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto deleteAddressFromPerson(Integer personId) {
        Person person = personRepository.findById(personId).get();
        person.setAddress(null);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto addSchoolClassToPerson(Integer personId, Integer schoolClassId) {
        Person person = personRepository.findById(personId).get();
        SchoolClass schoolClass = schoolClassRepository.findById(schoolClassId).get();
        if(Objects.nonNull(person.getSchoolClass())) {
            throw new RuntimeException("Person has already a CLass");
        }
        person.setSchoolClass(schoolClass);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }

    @Override
    public PersonResponseDto deleteSchoolClassFromPerson(Integer personId) {
        Person person = personRepository.findById(personId).get();
        person.setSchoolClass(null);
        personRepository.save(person);
        return mapper.personToPersonResponseDto(person);
    }
}
