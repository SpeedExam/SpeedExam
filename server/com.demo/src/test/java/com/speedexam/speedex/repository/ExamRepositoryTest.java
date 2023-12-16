package com.speedexam.speedex.repository;

import com.speedexam.speedex.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;

@DataJpaTest
class ExamRepositoryTest {

    @Autowired
    private  ExamService examRepository;
}