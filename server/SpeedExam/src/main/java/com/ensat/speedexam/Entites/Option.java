package com.ensat.speedexam.Entites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isCorrect;


    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
