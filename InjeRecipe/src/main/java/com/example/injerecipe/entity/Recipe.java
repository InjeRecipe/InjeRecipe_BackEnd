package com.example.injerecipe.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "recipe")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recipe {
    @Id
    private String id;

    private int recipeId;

    private String recipeNmKo;

    private String summary;

    private int nationCode;

    private String nationNm;

    private int tyCode;

    private String tyNm;

    private String cookingTime;

    private String calorie;

    private String qmt;

    private String levelNm;

    private String irdntCode;

    private String pcNm;

    @DBRef
    private Ingredient ingredient;
}
