package com.example.injerecipe.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Ingredient {
    private String irdntNm;

    private String irdntCpcty;

    private String irdntTyCode;

    private String irdntTyNm;

    @DBRef
    private Recipe recipe;
}
