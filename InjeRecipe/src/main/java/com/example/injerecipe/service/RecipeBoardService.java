package com.example.injerecipe.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.injerecipe.dto.request.RecipeBoardRequest;
import com.example.injerecipe.dto.request.RecipeSearchRequest;
import com.example.injerecipe.dto.response.RecipeBoardSearchResponse;
import com.example.injerecipe.dto.response.RecipeSearchResponse;
import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.Recipe;
import com.example.injerecipe.entity.RecipeBoard;
import com.example.injerecipe.repository.MemberRepository;
import com.example.injerecipe.repository.RecipeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeBoardService {

    private final RecipeBoardRepository recipeBoardRepository;

    private final MemberRepository memberRepository;

    private final AmazonS3Service amazonS3Service;



    public RecipeBoard registerRecipe(RecipeBoardRequest request, Long id) throws IOException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        // 이미지 업로드 및 URL 생성
        List<String> imageUrls = uploadImagesToS3(request.getRecipeImages());

        // 레시피 생성
        RecipeBoard.RecipeBoardBuilder recipeBoard = RecipeBoard.builder()
                .recipeSeq(request.getRecipeSeq())
                .recipeNm(request.getRecipeNm())
                .recipeWay(request.getRecipeWay())
                .recipePat(request.getRecipePat())
                .recipeEng(request.getRecipeEng())
                .recipeFileS(request.getRecipeFileS())
                .recipePartsDtls(request.getRecipePartsDtls())
                .recipeImages(imageUrls) // S3에 업로드된 이미지 URL 저장
                .recipeManuals(request.getRecipeManuals())
                .member(member);

        return recipeBoardRepository.save(recipeBoard.build());
    }


    public List<RecipeBoardSearchResponse> getPosts(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));
        List<RecipeBoard> recipeBoardList = recipeBoardRepository.findAllByMemberId(id);
        List<RecipeBoardSearchResponse> responseList = new ArrayList<>();
        for (RecipeBoard board : recipeBoardList) {
            responseList.add(RecipeBoardSearchResponse.from(board));
        }

        return responseList;
    }

    private List<String> uploadImagesToS3(List<MultipartFile> images) throws IOException {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            String imageUrl = amazonS3Service.uploadImage(image, fileName);
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }


    public List<RecipeSearchResponse> searchRecipes(RecipeSearchRequest request) {
        List<RecipeSearchResponse> searchResponses = new ArrayList<>();

        List<String> keywords = request.getKeywords();
        List<String> url = amazonS3Service.getImageUrlsWithKeyword("default");
        for (String keyword : keywords) {
            if (keyword != null) {
                List<RecipeBoard> recipes = recipeBoardRepository.findByRecipeNmContainingKeyword(keyword);
                if (!recipes.isEmpty()) {
                    searchResponses.add(RecipeSearchResponse.from(recipes.get(0)));
                } else {
                    searchResponses.add(RecipeSearchResponse.builder()
                            .recipeNm(keyword)
                            .recipeImages(amazonS3Service.getImageUrlsWithKeyword("default"))
                            .errorMessage("해당 레시피를 추가해주세요.")
                            .build());
                }
            }
        }
        return searchResponses;
    }



    public List<RecipeSearchResponse> searchRecipe(RecipeSearchRequest request) {
        List<RecipeBoard> recipeList = recipeBoardRepository.findByRecipeNmContainingKeyword(request.getKeywords().get(0));
        List<RecipeSearchResponse> searchResponses = new ArrayList<>();
        if(!searchResponses.isEmpty()) {
            for (RecipeBoard recipe : recipeList) {
                searchResponses.add(RecipeSearchResponse.from(recipe));
            }
        }
        else{
            searchResponses.add(RecipeSearchResponse.builder()
                    .recipeNm(request.getKeywords().get(0))
                    .recipeImages(amazonS3Service.getImageUrlsWithKeyword("default"))
                    .errorMessage("해당 레시피를 추가해주세요.")
                    .build());
        }
        return searchResponses;
    }


}
