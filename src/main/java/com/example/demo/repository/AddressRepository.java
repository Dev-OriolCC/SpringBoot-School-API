package com.example.demo.repository;

import com.example.demo.model.Address;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.ApiIgnore;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
