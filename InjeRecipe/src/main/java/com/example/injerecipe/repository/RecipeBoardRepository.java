package com.example.injerecipe.repository;

import com.example.injerecipe.entity.RecipeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeBoardRepository extends JpaRepository<RecipeBoard, Long> {
}
