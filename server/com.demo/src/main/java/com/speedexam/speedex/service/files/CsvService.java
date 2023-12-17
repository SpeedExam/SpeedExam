package com.speedexam.speedex.service.files;

import com.speedexam.speedex.model.files.Csv;
import com.speedexam.speedex.repository.files.CsvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CsvService {
    private final CsvRepository csvRepository;



    public void uploadCsv(MultipartFile file) throws IOException {
        Csv csvData = new Csv();
        csvData.setData(file.getBytes());

        csvRepository.save(csvData);
    }
    public Optional<Csv> getCsvDataById(Long id) {
        return csvRepository.findById(id);
    }
    // Additional methods for CSV data management if needed
}
