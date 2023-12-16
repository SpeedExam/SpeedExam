package com.speedexam.speedex.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

}
