package com.speedexam.speedex.repository;

import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository
extends JpaRepository<Option,Long> {
}
