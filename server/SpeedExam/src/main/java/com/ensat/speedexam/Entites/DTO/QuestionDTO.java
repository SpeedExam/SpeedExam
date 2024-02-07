package com.ensat.speedexam.Entites.DTO;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String name;
    private List<String> options;
    private int correctAns;
    private int timing;

    // Additional constructors or methods if needed
}

