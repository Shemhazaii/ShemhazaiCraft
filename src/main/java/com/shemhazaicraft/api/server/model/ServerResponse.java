package com.shemhazaicraft.api.server.model;

import com.shemhazaicraft.api.server.Server;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServerResponse {
    private String slug;
    private String name;
    private String hostname;
    private int port;
    private String description;
    private String objectKey;

    public static ServerResponse from(Server entity) {
        return new ServerResponse(
                entity.getSlug(),
                entity.getName(),
                entity.getHostname(),
                entity.getPort(),
                entity.getDescription(),
                entity.getObjectKey()
        );
    }
}
