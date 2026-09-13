package com.shemhazaicraft.api.server.status;

import com.shemhazaicraft.api.server.model.ServerStatusResponse;

import java.util.List;

public interface ServerStatusService {
    void checkAllServers();

    List<ServerStatusResponse> getAllStatuses();
}
