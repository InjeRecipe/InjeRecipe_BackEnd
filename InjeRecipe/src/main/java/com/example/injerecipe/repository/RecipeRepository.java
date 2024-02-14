package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<Recipe> findByRecipeId(String recipeId);
}
