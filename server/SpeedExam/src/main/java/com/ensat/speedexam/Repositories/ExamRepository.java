package com.ensat.speedexam.Repositories;

import com.ensat.speedexam.Entites.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExamRepository
extends JpaRepository<Exam,Long> {

}
