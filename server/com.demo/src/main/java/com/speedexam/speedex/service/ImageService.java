package com.speedexam.speedex.service;

import com.speedexam.speedex.model.Image;
import com.speedexam.speedex.repository.ImageReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageReposiroty imageRepository;



    public String uploadImage(MultipartFile file , String name) throws IOException {
        Image image = new Image();
        image.setData(file.getBytes());
        image.setName(name);
        imageRepository.save(image);
        return "Image uploaded successfully";
    }

    public byte[] downloadImage(Long id) {
        return imageRepository.findById(id)
                .map(Image::getData)
                .orElse(null);
    }
}