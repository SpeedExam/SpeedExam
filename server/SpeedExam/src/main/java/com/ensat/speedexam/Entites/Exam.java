package com.ensat.speedexam.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("exam") // Add this annotation to break the loop
    private List<Question> questions = new ArrayList<>();



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private float note;

    private int timeLimits; // it's in seconds

    private  boolean passed;

}
