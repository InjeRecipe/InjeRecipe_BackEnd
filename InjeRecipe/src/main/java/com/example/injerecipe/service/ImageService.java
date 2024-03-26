package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.ImageSearchRequest;
import com.example.injerecipe.dto.request.ImageUploadRequest;
import com.example.injerecipe.dto.response.ImageUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3Service amazonS3Service;


    public ImageUploadResponse registerImage(ImageUploadRequest request)throws IOException{
        String imageUrl = uploadImagesToS3(request.getImage());
        return ImageUploadResponse.from(request.getName(), imageUrl);
    }

    public List<List<String>> searchImage(ImageSearchRequest request){
        List<String> nameList = request.getName();
        List<List<String>> url = new ArrayList<>();
        for(String name: nameList) {
            url.add(amazonS3Service.getImageUrlsWithKeyword(name));
        }
        return url;
    }

    private String uploadImagesToS3(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String imageUrl = amazonS3Service.uploadImage(image, fileName);

        return imageUrl;
    }
}
