package com.example.injerecipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    private String recipeSeq;

    private String recipeNm;

    private String recipeWay;

    private String recipePat;

    private String recipeEng;

    private String recipeFileS;

    private String recipeImage;

    private String recipeManual;

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
}
