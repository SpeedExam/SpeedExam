package com.speedexam.speedex.model.files;

import com.speedexam.speedex.model.Person;
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
