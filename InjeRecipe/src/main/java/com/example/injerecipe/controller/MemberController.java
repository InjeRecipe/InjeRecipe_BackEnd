package com.example.injerecipe.controller;



import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.dto.request.MemberSignUpRequest;
import com.example.injerecipe.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/signUp")
    public ApiResponse signUp(@RequestBody MemberSignUpRequest request) throws Exception {

        return ApiResponse.success(memberService.signUp(request));
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
