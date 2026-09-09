package com.shemhazaicraft.api.server.status;

import java.time.Instant;

public record ServerStatus(
        boolean online,
        String version,
        int playersOnline,
        int playersMax,
        long latency
) {
    public static ServerStatus offline() {
        return new ServerStatus(
                false,
                null,
                0,
                0,
                0
        );
    }
}