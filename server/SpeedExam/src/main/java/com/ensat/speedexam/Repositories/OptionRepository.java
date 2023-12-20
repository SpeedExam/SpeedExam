package com.ensat.speedexam.Repositories;

import com.ensat.speedexam.Entites.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository
extends JpaRepository<Option,Long> {
}
