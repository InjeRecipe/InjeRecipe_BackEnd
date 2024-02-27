package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.RefrigeratorItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefrigeratorItemRepository extends JpaRepository<RefrigeratorItem, Long> {
    Optional<RefrigeratorItem> findByMember(Member member);
    Optional<RefrigeratorItem> findByIngredient(String Ingredient);
}
