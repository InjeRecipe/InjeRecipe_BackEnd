package com.example.injerecipe.controller;


import com.example.injerecipe.dto.UserSignUpDto;
import com.example.injerecipe.service.UserService;

import com.example.injerecipe.dto.request.MemberSignUpRequest;
import com.example.injerecipe.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);


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
