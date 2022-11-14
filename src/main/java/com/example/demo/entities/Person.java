package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    private int personId;

    private String name;

    private String mobileNumber;

    private String email;

    private String confirmEmail;

    private String pwd;

    private String confirmPwd;

    // Goood NOT
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Roles.class)
    //@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles roles;

    public Roles getRoles() { return roles; }
    public void setRoles(Roles roles) { this.roles = roles; }
    // Good
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    //**********************************************************************************
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private SchoolClass schoolClass;

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }
    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }


    // Goood!
    @ManyToMany()
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "person_courses",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "courseId")})
    private Set<Courses> courses = new HashSet<>();

    public Set<Courses> getCourses() {
        return courses;
    }
    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }


}
