package com.example.injerecipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecipeSearchRequest {
    @Schema(description = "검색어", example = "된장찌개")
    String keyword;
}
