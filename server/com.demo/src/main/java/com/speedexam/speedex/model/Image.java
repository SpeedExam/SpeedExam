package com.speedexam.speedex.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Lob
    private byte[] data;

    @OneToOne(mappedBy = "profilePicture")
    private Person person;

}
