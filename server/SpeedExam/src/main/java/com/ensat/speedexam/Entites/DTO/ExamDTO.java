package com.ensat.speedexam.Entites.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ExamDTO {
    private Long id;
    private String name;
    private List<QuestionDTO> questions;
    private float note;
    private int timeLimits;
    private boolean passed;
    private Long userId; // Include only the user ID
}
