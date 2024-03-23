package com.example.injerecipe.dto.response;

import com.example.injerecipe.dto.request.ImageUploadRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ImageUploadResponse {
    private String name;
    private String imageUrl;

    public static ImageUploadResponse from(String name, String imageUrl){
        return ImageUploadResponse.builder()
                .name(name)
                .imageUrl(imageUrl)
                .build();
    }
}
