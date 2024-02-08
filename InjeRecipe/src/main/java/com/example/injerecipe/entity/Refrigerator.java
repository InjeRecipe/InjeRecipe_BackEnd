package com.example.injerecipe.entity;

import com.example.injerecipe.dto.request.RefrigeratorRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "refrigerator")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Refrigerator {

    @Id
    private String id;

    private String ingredient;

    @DBRef
    private Member member;

    public static Refrigerator from(RefrigeratorRequest request, Member member){
        return Refrigerator.builder()
                .ingredient(request.ingredients())
                .member(member)
                .build();
    }

}
