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
    @Column(name = "member_id")
    private Long id;

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

}
