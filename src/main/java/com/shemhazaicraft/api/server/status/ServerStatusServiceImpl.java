package com.shemhazaicraft.api.server.status;

import com.shemhazaicraft.api.common.exception.NotFoundException;
import com.shemhazaicraft.api.server.Server;
import com.shemhazaicraft.api.server.ServerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerStatusServiceImpl implements ServerStatusService{

    private final ServerRepository serverRepository;
    private final MinecraftStatusClient minecraftStatusClient;
    private final ServerStatusCache statusCache;

    public ServerStatusServiceImpl(ServerRepository serverRepository, MinecraftStatusClient minecraftStatusClient, ServerStatusCache statusCache) {
        this.serverRepository = serverRepository;
        this.minecraftStatusClient = minecraftStatusClient;
        this.statusCache = statusCache;
    }

    @Override
    public void checkAllServers() {

        List<Server> servers = serverRepository.findAllByEnabledTrue().orElseThrow(
                () -> new NotFoundException("No servers found")
        );

        for (var server : servers) {
            checkServer(server);
        }
    }

    private void checkServer(Server server) {

        ServerStatus status =
                minecraftStatusClient.query(
                        server.getHostname(),
                        server.getPort()
                );

        statusCache.save(
                server.getSlug(),
                status
        );
    }
}
