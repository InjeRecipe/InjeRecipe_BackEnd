package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.IngredientsRequest;
import com.example.injerecipe.dto.request.RefrigeratorRequest;
import com.example.injerecipe.service.RefrigeratorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @Operation(summary = "재료 등록")
    @PostMapping("/ingredient")
    public ApiResponse addIngredientToRefrigerator(@RequestBody RefrigeratorRequest refrigeratorRequest) {
        return ApiResponse.success(refrigeratorService.addIngredientToRefrigerator(refrigeratorRequest));
    }

    @Operation(summary = "전체 재료 조회")
    @PostMapping("/ingredients")
    public ApiResponse getIngredients(@RequestBody IngredientsRequest request) {
        return ApiResponse.success(refrigeratorService.getItem(request));
    }


}
