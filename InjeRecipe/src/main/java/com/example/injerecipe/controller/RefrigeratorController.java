package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.RefrigeratorRequest;
import com.example.injerecipe.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping("/ingredient")
    public ApiResponse addIngredientToRefrigerator(@RequestBody RefrigeratorRequest refrigeratorRequest) {
        return ApiResponse.success(refrigeratorService.addIngredientToRefrigerator(refrigeratorRequest));
    }
}
