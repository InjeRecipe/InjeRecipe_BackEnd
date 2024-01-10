package com.example.injerecipe.auth.handler;

import com.example.injerecipe.dto.TokenDto;
import com.example.injerecipe.security.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    public static final String REDIRECT_URI = "http://localhost:3000/logincheck";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("SuccessHandler oAuth2User: {}", oAuth2User);

        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
        log.info("accessToken = {}, refreshToken = {}", tokenDto.getAccessToken(), tokenDto.getRefreshToken());

        String redirectUri = UriComponentsBuilder.fromUriString(REDIRECT_URI)
                .queryParam("accessToken", tokenDto.getAccessToken()) // setCookie
                .queryParam("refreshToken", tokenDto.getRefreshToken())
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        response.sendRedirect(redirectUri);
    }
}

