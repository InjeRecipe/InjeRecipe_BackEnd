package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
}
