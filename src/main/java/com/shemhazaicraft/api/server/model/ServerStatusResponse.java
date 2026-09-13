package com.shemhazaicraft.api.server.model;

import com.shemhazaicraft.api.server.status.ServerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServerStatusResponse {

    private String server;
    private ServerStatus status;
}


