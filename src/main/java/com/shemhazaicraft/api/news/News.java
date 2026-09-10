package com.shemhazaicraft.api.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String thumbnailObjectKey;

    @Enumerated(EnumType.STRING)

    @Column(nullable = false)
    private NewsStatus status;

    private Instant publishedAt;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant updatedAt;

}
