package com.shemhazaicraft.api.server;

import com.shemhazaicraft.api.common.exception.NotFoundException;
import com.shemhazaicraft.api.server.model.ServerRequest;
import com.shemhazaicraft.api.server.status.MinecraftStatusClient;
import com.shemhazaicraft.api.server.status.ServerStatus;
import com.shemhazaicraft.api.server.status.ServerStatusCache;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final ServerStatusCache statusCache;
    private final MinecraftStatusClient minecraftStatusClient;

    public ServerServiceImpl(ServerRepository serverRepository, ServerStatusCache statusCache, MinecraftStatusClient minecraftStatusClient) {
        this.serverRepository = serverRepository;
        this.statusCache = statusCache;
        this.minecraftStatusClient = minecraftStatusClient;
    }


    @Override
    public void save(ServerRequest server) {

        serverRepository.save(Server.builder()
                .name(server.getName())
                .slug(server.getSlug())
                .hostname(server.getHostname())
                .port(server.getPort())
                .description(server.getDescription())
                .enabled(true)
                .build());

    }

    @Override
    public ServerStatus getStatus(String slug) throws NotFoundException {

        Optional<ServerStatus> cached = statusCache.get(slug);

        if (cached.isPresent()) {
            return cached.get();
        }

        Server server = serverRepository.findServerBySlug(slug).orElseThrow(() -> new NotFoundException("Server not found"));

        ServerStatus status = minecraftStatusClient.query(
                server.getHostname(),
                server.getPort()
        );

        statusCache.save(slug, status);

        return status;
    }
}
