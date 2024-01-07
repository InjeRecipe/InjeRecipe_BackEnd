package com.example.injerecipe.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "member")
public class Member {
    private String id;
    private String name;
    private int age;

    public static Member from(String name, int age){
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
