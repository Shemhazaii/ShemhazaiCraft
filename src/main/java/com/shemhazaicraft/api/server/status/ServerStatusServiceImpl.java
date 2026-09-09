package com.shemhazaicraft.api.server.status;

import com.shemhazaicraft.api.common.exception.NotFoundException;
import com.shemhazaicraft.api.realtime.ServerEventService;
import com.shemhazaicraft.api.server.Server;
import com.shemhazaicraft.api.server.ServerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerStatusServiceImpl implements ServerStatusService{

    private final ServerRepository serverRepository;
    private final MinecraftStatusClient minecraftStatusClient;
    private final ServerStatusCache statusCache;
    private final ServerEventService eventService;

    public ServerStatusServiceImpl(ServerRepository serverRepository, MinecraftStatusClient minecraftStatusClient, ServerStatusCache statusCache, ServerEventService eventService) {
        this.serverRepository = serverRepository;
        this.minecraftStatusClient = minecraftStatusClient;
        this.statusCache = statusCache;
        this.eventService = eventService;
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

        ServerStatus previous =
                statusCache.get(server.getSlug())
                        .orElse(null);

        ServerStatus current =
                minecraftStatusClient.query(
                        server.getHostname(),
                        server.getPort()
                );

        statusCache.save(
                server.getSlug(),
                current
        );


        if (hasMeaningfulChange(previous, current)) {
            eventService.publishServerStatus(
                    server.getSlug(),
                    current
            );
        }
    }

    private boolean hasMeaningfulChange(ServerStatus previous, ServerStatus current){
        return previous == null || !previous.equals(current);
    }
}
