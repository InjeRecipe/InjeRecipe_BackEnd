package com.example.injerecipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record RefrigeratorRequest (
        @Schema(description = "아이디", example = "test1234")
        String account,
        @Schema(description = "레시피 재료", example = "파")
        String ingredient
){
}
