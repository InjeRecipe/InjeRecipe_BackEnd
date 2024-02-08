package com.example.injerecipe.entity;

import com.example.injerecipe.dto.Role;
import com.example.injerecipe.dto.SocialType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@Document(collection = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    private String id;

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




    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
