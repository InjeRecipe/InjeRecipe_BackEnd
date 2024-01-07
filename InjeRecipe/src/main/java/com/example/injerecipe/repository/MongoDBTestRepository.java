package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBTestRepository extends MongoRepository<Member, String> {
    Member findByName(String name);
}
