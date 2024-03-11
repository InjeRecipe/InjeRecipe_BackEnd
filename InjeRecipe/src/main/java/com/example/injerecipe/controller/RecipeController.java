package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.RecipeRequest;
import com.example.injerecipe.dto.request.RecipeSearchRequest;
import com.example.injerecipe.entity.Recipe;
import com.example.injerecipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Tag(name = "레시피 API")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(summary = "레시피 저장")
    @PostMapping("/recipe")
    public ApiResponse saveRecipe(@RequestBody RecipeRequest request)throws ParseException{
        return ApiResponse.success(recipeService.getRecipe(request.getStart(), request.getEnd()));
    }

    @Operation(summary = "레시피 검색")
    @PostMapping("/recipes")
    public ApiResponse searchRecipes(@RequestBody RecipeSearchRequest request) {

        return ApiResponse.success(recipeService.searchRecipes(request));
    }
}
