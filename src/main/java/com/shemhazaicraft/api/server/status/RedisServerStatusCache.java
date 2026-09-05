package com.shemhazaicraft.api.server.status;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisServerStatusCache implements ServerStatusCache{

    private static final String PREFIX = "server:status:";

    private final RedisTemplate<String, ServerStatus> redis;

    @Override
    public void save(String slug, ServerStatus status) {
        redis.opsForValue().set(
                PREFIX + slug,
                status,
                Duration.ofSeconds(45)
        );
    }

    @Override
    public Optional<ServerStatus> get(String slug) {
        ServerStatus status =
                redis.opsForValue().get(PREFIX + slug);

        return Optional.ofNullable(status);
    }

    @Override
    public void delete(String slug) {

    }
}
