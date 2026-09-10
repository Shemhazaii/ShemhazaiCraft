package com.shemhazaicraft.api.news;


import com.shemhazaicraft.api.news.models.NewsDetailResponse;
import com.shemhazaicraft.api.news.models.NewsSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NewsDetailResponse> save(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam("file") MultipartFile picture) {

        return ResponseEntity.ok(newsService.save(title, content, picture));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Void> publish(@PathVariable Long id) {
        newsService.publish(id);
        return null;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<NewsDetailResponse> getNewsDetail(@PathVariable String slug) {
        return ResponseEntity.ok(newsService.getDetail(slug));
    }


    @GetMapping
    public ResponseEntity<List<NewsSummaryResponse>> getNews() {

        return ResponseEntity.ok(newsService.getSummary());
    }


}
