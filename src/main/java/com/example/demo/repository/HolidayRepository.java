package com.example.demo.repository;

import com.example.demo.entities.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {
    List<Holiday> findAll();
    List<Holiday> findAllByType(Holiday.Type type);
}
