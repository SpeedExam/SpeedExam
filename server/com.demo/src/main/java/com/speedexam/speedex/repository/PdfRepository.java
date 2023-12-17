package com.speedexam.speedex.repository;

import com.speedexam.speedex.model.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository
extends JpaRepository<Pdf, Long>{


}
