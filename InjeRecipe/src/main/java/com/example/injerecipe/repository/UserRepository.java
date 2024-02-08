package com.example.injerecipe.repository;

import com.example.injerecipe.entity.Member;
import com.example.injerecipe.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<Member> findByEmailAndProvider(String email, String provider);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByRefreshToken(String refreshToken);

}
