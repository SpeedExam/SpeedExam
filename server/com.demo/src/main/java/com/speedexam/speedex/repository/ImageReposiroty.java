package com.speedexam.speedex.repository;

import com.speedexam.speedex.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageReposiroty
extends JpaRepository <Image, Long>{
}
