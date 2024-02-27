package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Tag(name = "레시피 API")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(summary = "재료정보")
    @GetMapping("/ingredients")
    public ResponseEntity<String> getIngredients(@RequestParam int start, @RequestParam int end){


        return new ResponseEntity<>(recipeService.getIngredients(start, end), HttpStatus.OK);
    }

    @Operation(summary = "과정정보")
    @GetMapping("/steps")
    public ResponseEntity<String> getSteps(@RequestParam int start, @RequestParam int end){


        return new ResponseEntity<>(recipeService.getSteps(start, end), HttpStatus.OK);
    }

    @Operation(summary = "기본정보")
    @GetMapping("/recipes")
    public ResponseEntity<String> getRecipes(@RequestParam int start, @RequestParam int end){

        return new ResponseEntity<>(recipeService.getRecipes(start, end), HttpStatus.OK);
    }

    @Operation(summary = "레시피 종합정보")
    @GetMapping("/recipe")
    public ApiResponse searchRecipe(@RequestParam int start, @RequestParam int end, @RequestParam String rcpNm){
        return ApiResponse.success(recipeService.getRecipe(start, end, rcpNm));
    }

}
