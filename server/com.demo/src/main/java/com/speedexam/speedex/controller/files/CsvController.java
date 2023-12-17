package com.speedexam.speedex.controller.files;

import com.speedexam.speedex.model.files.Csv;
import com.speedexam.speedex.service.files.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/csv")
@RequiredArgsConstructor
public class CsvController {

    private final CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvService.uploadCsv(file);
            return ResponseEntity.ok("CSV uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error uploading CSV: " + e.getMessage());
        }
    }
    @GetMapping("/{csvId}")
    public ResponseEntity<byte[]> downloadCsv(@PathVariable Long csvId) {
        Optional<Csv> optionalCsvData = csvService.getCsvDataById(csvId);

        if (optionalCsvData.isPresent()) {
            Csv csvData = optionalCsvData.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("data" + csvId + ".csv")
                    .build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvData.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
