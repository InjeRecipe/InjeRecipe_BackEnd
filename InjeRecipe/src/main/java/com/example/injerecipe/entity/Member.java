package com.example.injerecipe.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "member")
public class Member {
    @Id
    private String id;

    private String name;

    private String email;

    private String provider;

    private String nickname;


    public Member(String id, String name, String email, String provider, String nickname){
        this.id = id;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
    }

    public static Member from(String id, String name, String email, String provider, String nickname){
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
}
