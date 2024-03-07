package com.example.injerecipe.dto.response;

import com.example.injerecipe.entity.Recipe;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeSearchResponse {
    private String recipeSeq;

    private String recipeNm;

    private String recipeWay;

    private String recipePat;

    private String recipeEng;

    private String recipeFileS;

    private String recipePartsDtls;

    private String recipeImage1;

    private String recipeManual1;

    private String recipeImage2;

    private String recipeManual2;

    private String recipeImage3;

    private String recipeManual3;

    private String recipeImage4;

    private String recipeManual4;

    private String recipeImage5;

    private String recipeManual5;

    private String recipeImage6;

    private String recipeManual6;

    private String recipeImage7;

    private String recipeManual7;

    private String recipeImage8;

    private String recipeManual8;

    private String recipeImage9;

    private String recipeManual9;

    private String recipeImage10;

    private String recipeManual10;

    private String recipeImage11;

    private String recipeManual11;

    private String recipeImage12;

    private String recipeManual12;

    private String recipeImage13;

    private String recipeManual13;

    private String recipeImage14;

    private String recipeManual14;

    private String recipeImage15;

    private String recipeManual15;

    private String recipeImage16;

    private String recipeManual16;

    private String recipeImage17;

    private String recipeManual17;

    private String recipeImage18;

    private String recipeManual18;

    private String recipeImage19;

    private String recipeManual19;

    private String recipeImage20;

    private String recipeManual20;

    public static RecipeSearchResponse from(Recipe recipe){
        return RecipeSearchResponse.builder()
                .recipeSeq(recipe.getRecipeSeq())
                .recipeNm(recipe.getRecipeNm())
                .recipeWay(recipe.getRecipeWay())
                .recipePat(recipe.getRecipePat())
                .recipeEng(recipe.getRecipeEng())
                .recipeFileS(recipe.getRecipeFileS())
                .recipeImage1(recipe.getRecipeImage1())
                .recipeManual1(recipe.getRecipeManual1())
                .recipeImage2(recipe.getRecipeImage2())
                .recipeManual2(recipe.getRecipeManual2())
                .recipeImage3(recipe.getRecipeImage3())
                .recipeManual3(recipe.getRecipeManual3())
                .recipeImage4(recipe.getRecipeImage4())
                .recipeManual4(recipe.getRecipeManual4())
                .recipeImage5(recipe.getRecipeImage5())
                .recipeManual5(recipe.getRecipeManual5())
                .recipeImage6(recipe.getRecipeImage6())
                .recipeManual6(recipe.getRecipeManual6())
                .recipeImage7(recipe.getRecipeImage7())
                .recipeManual7(recipe.getRecipeManual7())
                .recipeImage8(recipe.getRecipeImage8())
                .recipeManual8(recipe.getRecipeManual8())
                .recipeImage9(recipe.getRecipeImage9())
                .recipeManual9(recipe.getRecipeManual9())
                .recipeImage10(recipe.getRecipeImage10())
                .recipeManual10(recipe.getRecipeManual10())
                .recipeImage11(recipe.getRecipeImage11())
                .recipeManual11(recipe.getRecipeManual11())
                .recipeImage12(recipe.getRecipeImage12())
                .recipeManual12(recipe.getRecipeManual12())
                .recipeImage13(recipe.getRecipeImage13())
                .recipeManual13(recipe.getRecipeManual13())
                .recipeImage14(recipe.getRecipeImage14())
                .recipeManual14(recipe.getRecipeManual14())
                .recipeImage15(recipe.getRecipeImage15())
                .recipeManual15(recipe.getRecipeManual15())
                .recipeImage16(recipe.getRecipeImage16())
                .recipeManual16(recipe.getRecipeManual16())
                .recipeImage17(recipe.getRecipeImage17())
                .recipeManual17(recipe.getRecipeManual17())
                .recipeImage18(recipe.getRecipeImage18())
                .recipeManual18(recipe.getRecipeManual18())
                .recipeImage19(recipe.getRecipeImage19())
                .recipeManual19(recipe.getRecipeManual19())
                .recipeImage20(recipe.getRecipeImage20())
                .recipeManual20(recipe.getRecipeManual20())
                .build();
    }
}
