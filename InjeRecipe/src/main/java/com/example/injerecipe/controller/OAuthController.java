package com.example.injerecipe.controller;

import com.example.injerecipe.dto.ApiResponse;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "OAuth API")
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @Operation(summary = "유저 정보")
    @GetMapping("/loginInfo")
    public String oauthLoginInfo(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        return attributes.toString();
    }

    @Operation(summary = "멤버 정보 저장")
    @PostMapping("/signUp")
    public ApiResponse oauthSignUp(@RequestBody Member member){
        return ApiResponse.success(oAuthService.saverUser(member));
    }

}
