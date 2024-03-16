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
@RequiredArgsConstructor
public class RecipeBoardService {

    private final RecipeBoardRepository recipeBoardRepository;

    private final MemberRepository memberRepository;

    private final AmazonS3Service amazonS3Service;


    @Transactional
    public RecipeBoard registerRecipe(RecipeBoardRequest request, Long id) throws IOException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        // 이미지 업로드 및 URL 생성
        List<String> imageUrls = uploadImagesToS3(request.getRecipe_images());

        // 레시피 생성
        RecipeBoard.RecipeBoardBuilder recipeBoard = RecipeBoard.builder()
                .recipeSeq(request.getRecipe_seq())
                .recipeNm(request.getRecipe_nm())
                .recipeWay(request.getRecipe_way())
                .recipePat(request.getRecipe_pat())
                .recipeEng(request.getRecipe_eng())
                .recipeFileS(request.getRecipe_file_s())
                .recipePartsDtls(request.getRecipe_parts_dtls())
                .recipeImages(imageUrls) // S3에 업로드된 이미지 URL 저장
                .recipeManuals(request.getRecipe_manuals())
                .member(member);

        return recipeBoardRepository.save(recipeBoard.build());
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

    @Transactional
    public List<RecipeBoardSearchResponse> searchRecipes(RecipeSearchRequest request) {
        List<RecipeBoardSearchResponse> searchResponses = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            String keyword = (String) invokeMethod(request, "getKeyword" + i);
            if (keyword != null) {
                List<RecipeBoard> recipes = recipeBoardRepository.findByRecipeNmContaining(keyword);
                if (!recipes.isEmpty()) {
                    searchResponses.add(RecipeBoardSearchResponse.from(recipes.get(0)));
                }
            }
        }

        return searchResponses;
    }

    @Transactional
    public List<RecipeBoardSearchResponse> searchRecipe(RecipeSearchRequest request) {
        List<RecipeBoard> recipeList = recipeBoardRepository.findByRecipeNmContaining(request.getKeyword1());
        List<RecipeBoardSearchResponse> searchResponses = new ArrayList<>();
        for(RecipeBoard recipe : recipeList){
            searchResponses.add(RecipeBoardSearchResponse.from(recipe));
        }

        return searchResponses;
    }


    private Object invokeMethod(Object obj, String methodName) {
        try {
            Method method = obj.getClass().getMethod(methodName);
            return method.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // 메서드가 없거나 호출할 수 없는 경우 null 반환
            return null;
        }
    }
}
