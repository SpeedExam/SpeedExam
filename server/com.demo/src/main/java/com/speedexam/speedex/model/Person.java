package com.speedexam.speedex.model;

import com.speedexam.speedex.model.files.Image;
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
    @Column
    private int age;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id")
    private Image profilePicture;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Exam> exams = new ArrayList<>();
}
