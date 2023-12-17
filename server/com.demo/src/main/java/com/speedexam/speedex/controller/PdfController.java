package com.speedexam.speedex.controller;

import com.speedexam.speedex.model.Pdf;
import com.speedexam.speedex.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

        private final PdfService pdfService;

        @PostMapping("/upload")
        public String uploadPdf(
            @RequestParam("file") MultipartFile file,
            String name
                    )throws IOException{
           return pdfService.uploadPdf(file , name);
        }

        @GetMapping("{id}")
    public ResponseEntity<byte[]> downloadPdf(
            @PathVariable Long id
        ){
            Optional<Pdf> optionalPdfDocument = pdfService.getPdfById(id);

            if (optionalPdfDocument.isPresent()) {
                Pdf pdfDocument = optionalPdfDocument.get();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", pdfDocument.getName());

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(pdfDocument.getData());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}

