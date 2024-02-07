package com.ensat.speedexam.Repositories;

import com.ensat.speedexam.Entites.Exam;
import com.ensat.speedexam.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExamRepository
extends JpaRepository<Exam,Long> {
    List<Exam> findAllByUser(User user);

}
