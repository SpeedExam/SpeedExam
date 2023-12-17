package com.speedexam.speedex.service.files;

import com.speedexam.speedex.model.files.Pdf;
import com.speedexam.speedex.repository.files.PdfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfRepository pdfRepository;

    public String uploadPdf(MultipartFile file,String name) throws IOException{
        Pdf pdf = new Pdf();
        pdf.setData(file.getBytes());
        pdf.setName(name);
        pdfRepository.save(pdf);
        return "PDF Stored Successfuly";

    }

    public Optional<Pdf> getPdfById(Long id){
        return pdfRepository.findById(id);

    }
}
