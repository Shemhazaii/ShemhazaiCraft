package com.shemhazaicraft.api.server.status;

import net.lenni0451.mcping.MCPing;
import net.lenni0451.mcping.responses.MCPingResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MinecraftStatusClientImpl implements MinecraftStatusClient{

    public ServerStatus query(String host, int port, String objectKey, String description, String server) {
        try {
            MCPingResponse response = MCPing
                    .pingModern()
                    .address(host, port)
                    .noResolve()
                    .timeout(3000, 3000)
                    .getSync();

            return new ServerStatus(
                    server,
                    ServerStatusState.ONLINE,
                    response.getVersionName(),
                    response.getOnlinePlayers(),
                    response.getMaxPlayers(),
                    response.getPing(),
                    objectKey,
                    description,
                    Instant.now()
            );

        } catch (Exception e) {
            return ServerStatus.offline(objectKey,description,server);
        }
    }
}
