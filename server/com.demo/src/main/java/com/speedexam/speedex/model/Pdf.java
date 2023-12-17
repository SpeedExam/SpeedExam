package com.speedexam.speedex.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = id + ".pdf";
        } else {
            this.name = name + ".pdf";
        }
    }
}
