package com.example.injerecipe.repository;


import com.example.injerecipe.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByEmailAndProvider(String email, String provider);

}
