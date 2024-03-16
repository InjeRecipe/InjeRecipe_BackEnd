package com.example.injerecipe.service;

import com.example.injerecipe.dto.Role;
import com.example.injerecipe.dto.request.MemberSignUpRequest;
import com.example.injerecipe.dto.request.SignInRequest;
import com.example.injerecipe.dto.response.MemberSignUpResponse;
import com.example.injerecipe.dto.response.SignInResponse;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.jwt.JwtTokenProvider;
import com.example.injerecipe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public MemberSignUpResponse signUp(MemberSignUpRequest request) throws Exception {

        if (memberRepository.findByEmail(request.getAccount()).isPresent()) {
            throw new Exception("이미 존재하는 계정입니다.");
        }
        if (memberRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        Member member = Member.builder()

                .account(request.getAccount())

                .password(request.getPassword())
                .nickname(request.getNickname())
                .age(request.getAge())
                .role(Role.USER)
                .build();

        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
        return MemberSignUpResponse.from(member);
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest request) {
        Member member = memberRepository.findByAccount(request.account())
                .filter(it -> passwordEncoder.matches(request.password(), it.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
        String token = tokenProvider.createToken(String.format("%s:%s", member.getId(), member.getRole()), member.getAccount());
        return new SignInResponse(member.getName(), member.getRole(), token);
    }
}

