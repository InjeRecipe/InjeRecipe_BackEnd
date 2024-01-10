package com.example.injerecipe.auth;

import com.example.injerecipe.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberProfile {
    private String name;
    private String email;
    private String provider;
    private String picture;

    public Member toMember(){
        return Member.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .build();
    }
}
