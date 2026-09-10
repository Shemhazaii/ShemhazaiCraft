package com.shemhazaicraft.api.news;

import com.shemhazaicraft.api.storage.ObjectStorageService;
import com.shemhazaicraft.api.news.models.NewsDetailResponse;
import com.shemhazaicraft.api.news.models.NewsSummaryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final ObjectStorageService storageService;

    public NewsServiceImpl(NewsRepository newsRepository, ObjectStorageService storageService) {
        this.newsRepository = newsRepository;
        this.storageService = storageService;
    }

    @Override
    public NewsDetailResponse save(String title, String content, MultipartFile picture) {

        String slug = title
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-");

        String objectKey =
                "news/"
                        + slug + "/"
                        + picture.getOriginalFilename();

        try {
            storageService.upload(
                    objectKey,
                    picture.getInputStream(),
                    picture.getSize(),
                    picture.getContentType()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        News news = new News();
        news.setTitle(title);
        news.setSlug(slug);
        news.setContent(content);
        news.setCreatedAt(Instant.now());
        news.setThumbnailObjectKey(objectKey);
        news.setStatus(NewsStatus.DRAFT);
        news.setPublishedAt(null);
        newsRepository.save(news);

        return NewsDetailResponse.from(news);

    }

    @Override
    public void delete(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow();
        newsRepository.delete(news);
    }

    @Override
    public void publish(Long id) {

        News news = newsRepository.findById(id)
                .orElseThrow();
        news.setStatus(NewsStatus.PUBLISHED);
        news.setPublishedAt(Instant.now());
        newsRepository.save(news);

    }

    @Override
    public void unpublish(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow();
        news.setStatus(NewsStatus.DRAFT);
        news.setPublishedAt(null);

    }

    @Override
    public void update(Long id, String title, String content, MultipartFile picture) {
        News news = newsRepository.findById(id)
                .orElseThrow();
        news.setTitle(title);
        news.setContent(content);
    }

    @Override
    public List<NewsSummaryResponse> getSummary() {
        return Objects.requireNonNull(newsRepository.findTop5ByPublishedAtIsNotNullOrderByCreatedAtDesc()).stream().map(NewsSummaryResponse::from).toList();
    }

    @Override
    public NewsDetailResponse getDetail(String slug) {

        News news = newsRepository.findBySlug(slug)
                .orElseThrow();
        return NewsDetailResponse.from(news);

    }

    @Override
    public NewsDetailResponse getLatest() {
        return null;
    }

    @Override
    public NewsDetailResponse getLatestPublished() {
        return null;
    }

    @Override
    public NewsDetailResponse getLatestUnpublished() {
        return null;
    }
}
