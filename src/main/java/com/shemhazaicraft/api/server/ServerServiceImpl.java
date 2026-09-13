package com.shemhazaicraft.api.server;

import com.shemhazaicraft.api.common.exception.NotFoundException;
import com.shemhazaicraft.api.server.model.ServerRequest;
import com.shemhazaicraft.api.server.model.ServerResponse;
import com.shemhazaicraft.api.server.status.MinecraftStatusClient;
import com.shemhazaicraft.api.server.status.ServerStatus;
import com.shemhazaicraft.api.server.status.ServerStatusCache;
import com.shemhazaicraft.api.storage.ObjectStorageService;
import com.shemhazaicraft.api.utils.FileUploadUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final ServerStatusCache statusCache;
    private final MinecraftStatusClient minecraftStatusClient;
    private final ObjectStorageService storageService;

    public ServerServiceImpl(ServerRepository serverRepository, ServerStatusCache statusCache, MinecraftStatusClient minecraftStatusClient, ObjectStorageService storageService) {
        this.serverRepository = serverRepository;
        this.statusCache = statusCache;
        this.minecraftStatusClient = minecraftStatusClient;
        this.storageService = storageService;
    }


    @Override
    public ServerResponse save(ServerRequest server) {

        String objectKey =
                "server/"
                        + server.getSlug() + "/"
                        + server.getFile().getOriginalFilename();

        String contentType = FileUploadUtils.getContentType(server.getFile());

        try {
            storageService.upload(
                    objectKey,
                    server.getFile().getInputStream(),
                    server.getFile().getSize(),
                    contentType
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        serverRepository.save(Server.builder()
                .name(server.getName())
                .slug(server.getSlug())
                .hostname(server.getHostname())
                .port(server.getPort())
                .description(server.getDescription())
                .objectKey(objectKey)
                .enabled(true)
                .build());

        return ServerResponse.from(serverRepository.findServerBySlug(server.getSlug()).orElseThrow());

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
                server.getPort(),
                server.getObjectKey()
        );

        statusCache.save(slug, status);

        return status;
    }
}
