package com.speedexam.speedex.model.files;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Csv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data; // Store CSV file content as byte array

    // Other necessary fields and associations
}