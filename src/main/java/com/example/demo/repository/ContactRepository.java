package com.example.demo.repository;

import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int sendContactMessage(Contact contact) {
        String sql = "insert into contact_msg (name, mobile_num, email, subject, message, status," +
                "created_at, created_by) values (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(), contact.getEmail(), contact.getSubject(),
                contact.getMessage(), contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
    }

}
