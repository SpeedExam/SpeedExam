package com.ensat.speedexam.Repositories;

import com.ensat.speedexam.Entites.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
extends JpaRepository<Person,Long> {


}
