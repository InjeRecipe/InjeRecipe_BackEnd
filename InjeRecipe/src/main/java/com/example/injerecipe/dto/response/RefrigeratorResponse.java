package com.example.injerecipe.dto.response;

import com.example.injerecipe.entity.Refrigerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record RefrigeratorResponse(
        @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
        String id,
        @Schema(description = "레시피 재료", example = "파, 마늘")
        String ingredients
) {
    public static RefrigeratorResponse from(Refrigerator refrigerator){
        return new RefrigeratorResponse(
                refrigerator.getId(),
                refrigerator.getIngredient()
        );
    }
}
