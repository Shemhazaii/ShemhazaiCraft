package com.shemhazaicraft.api.server;

import jakarta.persistence.*;

@Entity
@Table(name = "servers")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    private String name;

    private String hostname;
    private int port;

    private String description;

    private String minecraftVersion;
    private String loader;

    private boolean enabled;

}
