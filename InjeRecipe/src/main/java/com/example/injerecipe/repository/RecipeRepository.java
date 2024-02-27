package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeId(Long recipeId);
}