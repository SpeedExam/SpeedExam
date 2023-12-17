package com.speedexam.speedex.repository.files;

import com.speedexam.speedex.model.files.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository
extends JpaRepository<Pdf, Long>{


}
