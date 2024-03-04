package com.example.injerecipe.entity;

import com.example.injerecipe.dto.Role;
import com.example.injerecipe.dto.SocialType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Getter
@Builder
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String account;

    private String name;

    private String email;

    private String password;

    private String provider;

    private String nickname;

    private String refreshToken;

    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String imageUrl;


    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

    public void updateAge(int updateAge) {
        this.age = updateAge;
    }


    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(updatePassword);
    }



    public Member(Long id, String name, String email, String provider, String nickname){
        this.id = id;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
    }

    public static Member from(Long id, String name, String email, String provider, String nickname){
        return Member.builder()
                .id(id)
                .name(name)
                .email(email)
                .provider(provider)
                .nickname(nickname)
                .build();
    }

    public Member update(String name, String email){
        this.name = name;
        this.email = email;
        return this;
    }

    public Member updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }

}
