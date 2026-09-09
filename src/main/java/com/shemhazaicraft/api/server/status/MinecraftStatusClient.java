package com.shemhazaicraft.api.server.status;

import lombok.RequiredArgsConstructor;
import net.lenni0451.mcping.MCPing;
import net.lenni0451.mcping.responses.MCPingResponse;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MinecraftStatusClient {

    public ServerStatus query(String host, int port) {
        try {
            MCPingResponse response = MCPing
                    .pingModern()
                    .address(host, port)
                    .noResolve()
                    .timeout(3000, 3000)
                    .getSync();

            return new ServerStatus(
                    true,
                    response.getVersionName(),
                    response.getOnlinePlayers(),
                    response.getMaxPlayers(),
                    response.getPing()
            );

        } catch (Exception e) {
            return ServerStatus.offline();
        }
    }
}
