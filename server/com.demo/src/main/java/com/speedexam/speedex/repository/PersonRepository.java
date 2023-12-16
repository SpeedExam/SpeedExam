package com.speedexam.speedex.repository;

import com.speedexam.speedex.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
extends JpaRepository<Person,Long> {


}
