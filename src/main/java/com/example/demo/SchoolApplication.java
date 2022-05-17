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
	 * 1.- Fix relationship issue at the moment of fetching data from Person -> SchoolClass [DONE] & Role [DONE]
	 * 		-> Apparently relationship is working fine!
	 * 2.- Re detail responses (dto's)
	 * 3.- Test Creation
	 * 4.- Connect to front-end or at least document
	 * */

}
