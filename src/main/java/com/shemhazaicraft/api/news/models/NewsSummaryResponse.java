package com.shemhazaicraft.api.news.models;

import com.shemhazaicraft.api.news.News;

import java.time.Instant;

public record NewsSummaryResponse(
        Long id,
        String title,
        String slug,
        String thumbnailUrl,
        Instant publishedAt
) {
    public static NewsSummaryResponse from(News news){
        return new NewsSummaryResponse(
                news.getId(),
                news.getTitle(),
                news.getSlug(),
                news.getThumbnailObjectKey(),
                news.getPublishedAt()
        );
    }
}
