package com.shemhazaicraft.api.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findTop5ByPublishedAtIsNotNullOrderByCreatedAtDesc();
    Optional<News> findBySlug(String slug);

}
