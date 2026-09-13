package com.shemhazaicraft.api.server.status;


import com.shemhazaicraft.api.server.model.ServerStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServerStatusController {

    private final ServerStatusService serverStatusService;

    @GetMapping("/status")
    public List<ServerStatusResponse> getStatuses() {
        return serverStatusService.getAllStatuses();
    }
}
