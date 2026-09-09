package com.shemhazaicraft.api.server;

import com.shemhazaicraft.api.server.model.ServerRequest;
import com.shemhazaicraft.api.server.status.ServerStatus;

public interface ServerService {

    void save(ServerRequest server);

    ServerStatus getStatus(String slug);
}
