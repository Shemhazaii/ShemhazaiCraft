package com.shemhazaicraft.api.server.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServerRequest {

    private String slug;
    private String name;
    private String hostname;
    private int port;
    private String description;
}
