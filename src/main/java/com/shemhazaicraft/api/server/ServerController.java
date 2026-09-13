package com.shemhazaicraft.api.server;


import com.shemhazaicraft.api.server.model.ServerRequest;
import com.shemhazaicraft.api.server.model.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.core.Validate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/servers")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ServerResponse> save(@ModelAttribute @Validate ServerRequest serverRequest) {

        return ResponseEntity.ok(serverService.save(serverRequest));
    }
}
