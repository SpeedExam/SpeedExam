package com.speedexam.speedex.controller.files;


import com.speedexam.speedex.service.files.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("file")MultipartFile file,
            String name
            ) throws IOException{
        return imageService.uploadImage(file, name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        byte[] imageData = imageService.downloadImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Change to the appropriate media type

        return ResponseEntity.ok()
                .headers(headers)
                .body(imageData);
    }

}
