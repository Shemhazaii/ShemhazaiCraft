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
                        .hostname("mc.shemhazaicraft.com")
                        .port(25565)
                        .description("Minecraft Proxy Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-realcraft")
                .name("Minecraft Realcraft")
                .hostname("mc.shemhazaicraft.com")
                .port(25566)
                .description("Minecraft Main Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-realcraft-exploration")
                .name("Minecraft Realcraft Exploration")
                .hostname("mc.shemhazaicraft.com")
                .port(25567)
                .description("Minecraft Main Exploration Server")
                .build());

        serverService.save(ServerRequest.builder()
                .slug("minecraft-cookcraft")
                .name("Minecraft Cook Craft")
                .hostname("mc.shemhazaicraft.com")
                .port(25568)
                .description("Minecraft Cooking Server")
                .build());



    }
}
