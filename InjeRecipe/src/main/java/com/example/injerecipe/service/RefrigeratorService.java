package com.example.injerecipe.service;

import com.example.injerecipe.common.SuccessResponse;
import com.example.injerecipe.dto.request.RefrigeratorRequest;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.RefrigeratorItem;
import com.example.injerecipe.repository.MemberRepository;
import com.example.injerecipe.repository.RefrigeratorItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorItemRepository refrigeratorItemRepository;

    private final MemberRepository memberRepository;

    private String notExist = "계정이 존재하지 않습니다.";

//    public RefrigeratorResponse saveIngredients(RefrigeratorRequest request, Long account){
//        Member member = memberRepository.findById(account).orElseThrow(() -> new UsernameNotFoundException("계정이 존재하지 않습니다"));
//
//        Refrigerator refrigerator = refrigeratorItemRepository.save(Refrigerator.from(request, member));
//
//        return RefrigeratorResponse.from(refrigerator);
//    }

    @Transactional
    public SuccessResponse addIngredientToRefrigerator(RefrigeratorRequest refrigeratorRequest) {
        Member loginUser = memberRepository.findByAccount(refrigeratorRequest.account()).orElseThrow(() -> new UsernameNotFoundException(notExist));
        Optional<RefrigeratorItem> item = refrigeratorItemRepository.findByIngredient(refrigeratorRequest.ingredient());
        if(item.isEmpty())
            refrigeratorItemRepository.save(RefrigeratorItem.from(loginUser, refrigeratorRequest.ingredient()));
        else{
            refrigeratorItemRepository.delete(item.get());
            refrigeratorItemRepository.flush();
        }
        System.out.println("----------------");
        return SuccessResponse.of(HttpStatus.OK, "성공!");
    }
}
