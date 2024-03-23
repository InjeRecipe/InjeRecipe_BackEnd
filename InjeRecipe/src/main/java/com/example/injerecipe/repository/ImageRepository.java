package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
