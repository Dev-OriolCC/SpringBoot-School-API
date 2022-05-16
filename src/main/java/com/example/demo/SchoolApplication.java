package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SchoolApplication {

	public static void main(String[] args) {

		SpringApplication.run(SchoolApplication.class, args);

	}
	/* NOTES:
	 * 1.- Fix relationship issue at the moment of fetching data from Person -> SchoolClass & Role [DONE]
	 * 2.- Change roleId relationship to use the object instead.
	 * */

}
