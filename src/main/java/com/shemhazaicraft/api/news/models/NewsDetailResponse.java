package com.shemhazaicraft.api.news.models;

import com.shemhazaicraft.api.news.News;

import java.time.Instant;

public record NewsDetailResponse(
        Long id,
        String title,
        String slug,
        String content,
        String thumbnailUrl,
        Instant publishedAt,
        Instant updatedAt
) {
    public static NewsDetailResponse from(News news) {
        return new NewsDetailResponse(
                news.getId(),
                news.getTitle(),
                news.getSlug(),
                news.getContent(),
                news.getThumbnailObjectKey(),
                news.getPublishedAt(),
                news.getUpdatedAt()
        );
    }
}
