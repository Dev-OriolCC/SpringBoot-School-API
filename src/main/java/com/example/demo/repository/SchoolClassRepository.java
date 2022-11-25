package com.example.demo.repository;

import com.example.demo.entities.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends CrudRepository<SchoolClass, Integer> {

    SchoolClass readByClassId(int classId);
}
