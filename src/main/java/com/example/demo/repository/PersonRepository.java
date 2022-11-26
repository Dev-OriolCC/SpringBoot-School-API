package com.example.demo.repository;

import com.example.demo.entities.Courses;
import com.example.demo.entities.Person;
import com.example.demo.entities.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person readByEmail(String email);
    List<Person> findAllBySchoolClass(SchoolClass schoolClass);
    List<Person> getBySchoolClass(SchoolClass schoolClass);
    Person findByEmail(String email);
    List<Person> findAllByCourses(Optional<Courses> courses);
}
