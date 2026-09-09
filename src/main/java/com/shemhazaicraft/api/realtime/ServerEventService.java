package com.shemhazaicraft.api.realtime;

import com.shemhazaicraft.api.server.status.ServerStatus;

public interface ServerEventService {

    void publishServerStatus(
            String serverSlug,
            ServerStatus status
    );

}
