package com.example.injerecipe.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    @Value("${recipeOpenApi.serviceKey}")
    private String serviceKey;

}
