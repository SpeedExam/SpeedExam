package com.ensat.speedexam.Entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Exam> exams = new ArrayList<>();
}
