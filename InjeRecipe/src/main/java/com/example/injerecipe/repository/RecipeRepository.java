package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByRecipeNmContaining(String keyword);
}
