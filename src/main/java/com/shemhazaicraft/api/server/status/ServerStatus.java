package com.shemhazaicraft.api.server.status;

import java.time.Instant;

public record ServerStatus(
        String server,
        ServerStatusState state,
        String version,
        int playersOnline,
        int playersMax,
        long latency,
        String objectKey,
        String description,
        Instant checkedAt
) {
    public static ServerStatus offline(String objectKey, String description, String server) {
        return new ServerStatus(
                server,
                ServerStatusState.OFFLINE,
                null,
                0,
                0,
                0,
                objectKey,
                description,
                Instant.now()
        );
    }
}