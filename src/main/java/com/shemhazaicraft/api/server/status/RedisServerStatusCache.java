package com.shemhazaicraft.api.server.status;

import com.shemhazaicraft.api.common.exception.NotFoundException;
import com.shemhazaicraft.api.server.Server;
import com.shemhazaicraft.api.server.ServerRepository;
import com.shemhazaicraft.api.server.model.ServerStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServerStatusCache implements ServerStatusCache {

    private static final String PREFIX = "server:status:";

    private final RedisTemplate<String, ServerStatus> redis;
    private final ServerRepository serverRepository;

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
        redis.delete(PREFIX + slug);
    }

    @Override
    public List<ServerStatusResponse> getAll() {

        List<ServerStatus> statuses =
                redis.opsForValue().multiGet(redis.keys(PREFIX + "*"));

        Map<String, Server> servers = serverRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Server::getObjectKey,
                        Function.identity()
                ));

        return statuses.stream()
                .map(status -> {
                    Server server = servers.get(status.objectKey());

                    if (server == null) {
                        throw new NotFoundException(
                                "Server not found: " + status.objectKey()
                        );
                    }

                    return new ServerStatusResponse(
                            server.getSlug(),
                            status
                    );
                })
                .toList();
    }
}
