package com.ensat.speedexam.Repositories;

import com.ensat.speedexam.Entites.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
extends JpaRepository<Question,Long> {

}
