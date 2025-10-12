package com.myorganisation.technova.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String course = "OBSOLETE_FIELD";
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    private List<Course> courses = new ArrayList<>();
}
