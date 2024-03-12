package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.RecipeBoardRequest;
import com.example.injerecipe.service.RecipeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class RecipeBoardController {
    private final RecipeBoardService recipeBoardService;

    @PostMapping("/write")
    public ApiResponse registerRecipe(@RequestBody RecipeBoardRequest request) {
        System.out.println(":::::::::::::::::::" + request.getEmail());
            return ApiResponse.success(recipeBoardService.registerRecipe(request));
    }
}
