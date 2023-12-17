package com.speedexam.speedex.repository.files;


import com.speedexam.speedex.model.files.Csv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvRepository
extends JpaRepository <Csv, Long>{

}