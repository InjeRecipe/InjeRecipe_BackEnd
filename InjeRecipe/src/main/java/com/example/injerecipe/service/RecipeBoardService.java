package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.RecipeBoardRequest;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.RecipeBoard;
import com.example.injerecipe.repository.MemberRepository;
import com.example.injerecipe.repository.RecipeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecipeBoardService {

    private final RecipeBoardRepository recipeBoardRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public RecipeBoard registerRecipe(RecipeBoardRequest request) {
        Member member = memberRepository.findByAccount(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));
        System.out.println("-----------------------------" + request.getRecipeSeq());
        RecipeBoard.RecipeBoardBuilder recipeBoard = RecipeBoard.builder()
                .recipeSeq(request.getRecipeSeq())
                .recipeNm(request.getRecipeNm())
                .recipeWay(request.getRecipeWay())
                .recipePat(request.getRecipePat())
                .recipeEng(request.getRecipeEng())
                .recipeFileS(request.getRecipeFileS())
                .recipePartsDtls(request.getRecipePartsDtls())
                .recipeImages(request.getRecipeImages())
                .recipeManuals(request.getRecipeManuals())
                .member(member);
        return recipeBoardRepository.save(recipeBoard.build());
    }
}
