package com.shemhazaicraft.api.server.status;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerStatusScheduler {

    private final ServerStatusService statusService;

    @Scheduled(fixedDelay = 15_000)
    public void checkServers() {
        statusService.checkAllServers();
    }
}
