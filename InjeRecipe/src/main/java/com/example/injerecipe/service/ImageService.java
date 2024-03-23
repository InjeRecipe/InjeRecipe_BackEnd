package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.ImageUploadRequest;
import com.example.injerecipe.dto.response.ImageUploadResponse;
import com.example.injerecipe.entity.Image;
import com.example.injerecipe.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3Service amazonS3Service;

    private final ImageRepository imageRepository;

    public ImageUploadResponse registerImage(ImageUploadRequest request)throws IOException{
        String imageUrl = uploadImagesToS3(request.getImage());
        imageRepository.save(Image.from(request.getName(), imageUrl));
        return ImageUploadResponse.from(request.getName(), imageUrl);
    }

    private String uploadImagesToS3(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String imageUrl = amazonS3Service.uploadImage(image, fileName);

        return imageUrl;
    }
}
