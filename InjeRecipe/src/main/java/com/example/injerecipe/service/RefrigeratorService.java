package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.RefrigeratorRequest;
import com.example.injerecipe.dto.response.RefrigeratorResponse;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.Refrigerator;
import com.example.injerecipe.repository.MemberRepository;
import com.example.injerecipe.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final MemberRepository memberRepository;

    public RefrigeratorResponse saveIngredients(RefrigeratorRequest request, Long account){
        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다"));

        Refrigerator refrigerator = refrigeratorRepository.save(Refrigerator.from(request, member));

        return RefrigeratorResponse.from(refrigerator);
    }
}
