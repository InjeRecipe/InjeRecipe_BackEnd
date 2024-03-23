package com.example.injerecipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String name;

    private String imageUrl;

    public static Image from(String name, String imageUrl){
        return Image.builder()
                .name(name)
                .imageUrl(imageUrl)
                .build();
    }
}
