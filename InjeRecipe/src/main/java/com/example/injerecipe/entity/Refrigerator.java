package com.example.injerecipe.entity;

import com.example.injerecipe.dto.request.RefrigeratorRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Refrigerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String ingredient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Refrigerator from(RefrigeratorRequest request, Member member){
        return Refrigerator.builder()
                .ingredient(request.ingredients())
                .member(member)
                .build();
    }

}
