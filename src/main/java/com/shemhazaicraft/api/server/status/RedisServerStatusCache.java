package com.shemhazaicraft.api.server.status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServerStatusCache implements ServerStatusCache{

    private static final String PREFIX = "server:status:";

    private final RedisTemplate<String, ServerStatus> redis;

    @Override
    public void save(String slug, ServerStatus status) {
        log.info("Saving server status for slug: {}", slug);
        redis.opsForValue().set(
                PREFIX + slug,
                status,
                Duration.ofSeconds(45)
        );
        log.info("Server status saved for slug: {}", slug);
    }

    @Override
    public Optional<ServerStatus> get(String slug) {
        ServerStatus status =
                redis.opsForValue().get(PREFIX + slug);

        return Optional.ofNullable(status);
    }

    @Override
    public void delete(String slug) {
        redis.delete(PREFIX + slug);
    }
}
