package com.shemhazaicraft.api.server.status;

import java.time.Instant;

public record ServerStatus(
        boolean online,
        int players,
        int maxPlayers,
        String version,
        long latency,
        Instant checkedAt
) {}