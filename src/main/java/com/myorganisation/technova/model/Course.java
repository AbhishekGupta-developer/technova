package com.myorganisation.technova.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double fee;
    private Double duration;
    private String faculty;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
