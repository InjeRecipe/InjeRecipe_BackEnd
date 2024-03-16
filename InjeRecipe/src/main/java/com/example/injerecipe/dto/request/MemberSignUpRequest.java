package com.example.injerecipe.dto.request;

import com.example.injerecipe.dto.Role;
import com.example.injerecipe.dto.SocialType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class MemberSignUpRequest {

    private String name;

    private String email;

    private String password;

    private String provider;

    private String nickname;

    private String refreshToken;

    private int age;

    private Role role;

    private SocialType socialType;

    private String socialId;

    private String imageUrl;
}
