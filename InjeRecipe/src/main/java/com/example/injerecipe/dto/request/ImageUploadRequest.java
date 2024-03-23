package com.example.injerecipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {
    @Schema(description = "사진 이름", example = "마늘")
    private String name;

    private MultipartFile image;
}
