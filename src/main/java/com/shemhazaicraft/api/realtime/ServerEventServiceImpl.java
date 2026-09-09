package com.shemhazaicraft.api.realtime;

import com.shemhazaicraft.api.realtime.model.ServerStatusEvent;
import com.shemhazaicraft.api.server.status.ServerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServerEventServiceImpl implements ServerEventService{

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void publishServerStatus(String serverSlug, ServerStatus status) {

        var event = new ServerStatusEvent(
                serverSlug,
                status
        );

        messagingTemplate.convertAndSend(
                "/topic/server-status",
                event
        );

    }

}

