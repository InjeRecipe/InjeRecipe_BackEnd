package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Recipe;
import com.example.injerecipe.entity.RecipeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeBoardRepository extends JpaRepository<RecipeBoard, Long> {
    List<RecipeBoard> findByRecipeNmContaining(String keyword);
}
