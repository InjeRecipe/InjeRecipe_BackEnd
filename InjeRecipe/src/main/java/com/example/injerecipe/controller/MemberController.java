package com.example.injerecipe.controller;



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
    public String signUp(@RequestBody MemberSignUpRequest request) throws Exception {
        memberService.signUp(request);

        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
