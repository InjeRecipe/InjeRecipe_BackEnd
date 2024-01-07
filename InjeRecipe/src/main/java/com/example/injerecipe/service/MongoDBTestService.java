package com.example.injerecipe.service;

import com.example.injerecipe.entity.Member;
import com.example.injerecipe.repository.MongoDBTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoDBTestService {
    private final MongoDBTestRepository mongoDBTestRepository;

    public Member selectUser(String name){
        return mongoDBTestRepository.findByName(name);
    }

    public void saveUser(String name, int age){
        mongoDBTestRepository.save(Member.from(name, age));
    }
}
