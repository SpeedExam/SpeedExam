package com.speedexam.speedexam.repository;

import com.speedexam.speedexam.model.ERole;
import com.speedexam.speedexam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
