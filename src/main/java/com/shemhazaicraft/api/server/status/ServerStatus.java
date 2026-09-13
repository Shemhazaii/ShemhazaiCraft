package com.shemhazaicraft.api.server.status;

import java.time.Instant;

public record ServerStatus(
        ServerStatusState state,
        String version,
        int playersOnline,
        int playersMax,
        long latency,
        String objectKey,
        Instant checkedAt
) {
    public static ServerStatus offline(String objectKey) {
        return new ServerStatus(
                ServerStatusState.OFFLINE,
                null,
                0,
                0,
                0,
                objectKey,
                Instant.now()
        );
    }
}