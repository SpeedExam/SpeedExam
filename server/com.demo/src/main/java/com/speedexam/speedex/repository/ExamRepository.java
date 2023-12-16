package com.speedexam.speedex.repository;

import com.speedexam.speedex.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExamRepository
extends JpaRepository<Exam,Long> {

}
