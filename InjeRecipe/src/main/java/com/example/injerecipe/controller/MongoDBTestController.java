package com.example.injerecipe.controller;

import com.example.injerecipe.entity.Member;
import com.example.injerecipe.service.MongoDBTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/mongo")
@RequiredArgsConstructor
public class MongoDBTestController {


    private final MongoDBTestService mongoDBTestService;

    @GetMapping(value = "/find")
    public Member findUserData(@RequestParam String name) {
        return mongoDBTestService.selectUser(name);
    }

    @GetMapping(value = "/save")
    public Member saveUserData(@RequestParam String name, @RequestParam int age) {
        log.info("[Controller][Recv] name : {}, age : {}", name, age);
        mongoDBTestService.saveUser(name, age);

        return mongoDBTestService.selectUser(name);
    }
}
