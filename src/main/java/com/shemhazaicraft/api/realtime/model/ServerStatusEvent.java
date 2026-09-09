package com.shemhazaicraft.api.realtime.model;

import com.shemhazaicraft.api.server.status.ServerStatus;

public record ServerStatusEvent(
        String server,
        ServerStatus status
) {
}
