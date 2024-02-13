package com.example.injerecipe.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSignUpRequest {

    private String email;
    private String password;
    private String nickname;
    private int age;
}
