package com.example.injerecipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RefrigeratorRequest (
        @Schema(description = "레시피 재료", example = "파, 마늘")
        String ingredients
){
}
