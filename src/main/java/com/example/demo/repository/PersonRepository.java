package com.example.demo.repository;

import com.example.demo.model.Courses;
import com.example.demo.model.Person;
import com.example.demo.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
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
