package com.example.injerecipe.repository;


import com.example.injerecipe.dto.SocialType;
import com.example.injerecipe.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByEmailAndProvider(String email, String provider);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByRefreshToken(String refreshToken);
    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

}
