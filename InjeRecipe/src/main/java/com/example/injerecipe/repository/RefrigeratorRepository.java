package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Refrigerator;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends MongoRepository<Refrigerator, String> {
}
