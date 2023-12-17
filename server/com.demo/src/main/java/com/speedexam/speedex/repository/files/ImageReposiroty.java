package com.speedexam.speedex.repository.files;

import com.speedexam.speedex.model.files.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageReposiroty
extends JpaRepository <Image, Long>{
}
