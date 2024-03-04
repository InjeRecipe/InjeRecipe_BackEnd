package com.example.injerecipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequest {
    @Schema(description = "이미지 이름", example = "통장사본")
    private String keyName;
}
