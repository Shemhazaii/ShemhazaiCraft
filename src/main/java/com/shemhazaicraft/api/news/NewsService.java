package com.shemhazaicraft.api.news;

import com.shemhazaicraft.api.news.models.NewsDetailResponse;
import com.shemhazaicraft.api.news.models.NewsSummaryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    NewsDetailResponse save(String title, String content, MultipartFile picture);
    void delete(Long id);
    void publish(Long id);
    void unpublish(Long id);
    void update(Long id, String title, String content, MultipartFile picture);
    List<NewsSummaryResponse> getSummary();
    NewsDetailResponse getDetail(String slug);
    NewsDetailResponse getLatest();
    NewsDetailResponse getLatestPublished();
    NewsDetailResponse getLatestUnpublished();
}
