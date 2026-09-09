package com.shemhazaicraft.api.utils;

import com.shemhazaicraft.api.server.ServerRepository;
import com.shemhazaicraft.api.server.ServerService;
import com.shemhazaicraft.api.server.model.ServerRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ServerRepository serverRepository;
    private final ServerService serverService;

    @Override
    public void run(String @NonNull ... args) throws Exception {

        serverRepository.deleteAll();

        serverService.save(ServerRequest.builder()
                        .slug("minecraft-proxy")
                        .name("Minecraft Proxy")
                        .hostname("localhost")
                        .port(30065)
                        .description("Minecraft Proxy Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-realcraft")
                .name("Minecraft Realcraft")
                .hostname("localhost")
                .port(30065)
                .description("Minecraft Main Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-realcraft-exploration")
                .name("Minecraft Realcraft Exploration")
                .hostname("localhost")
                .port(30065)
                .description("Minecraft Main Exploration Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-cookcraft")
                .name("Minecraft Cook Craft")
                .hostname("localhost")
                .port(30065)
                .description("Minecraft Cooking Server")
                .build());



    }
}
