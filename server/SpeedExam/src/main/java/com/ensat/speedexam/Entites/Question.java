package com.ensat.speedexam.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
