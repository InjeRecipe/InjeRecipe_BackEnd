package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.RefrigeratorRequest;
import com.example.injerecipe.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping("/register")
    public ApiResponse registerIngredients(@RequestBody RefrigeratorRequest request, @RequestParam Long account){
        return ApiResponse.success(refrigeratorService.saveIngredients(request, account));
    }
}
