package com.shemhazaicraft.api.server.status;

import com.shemhazaicraft.api.server.model.ServerStatusResponse;

import java.util.List;
import java.util.Optional;

public interface ServerStatusCache {

    void save(String slug, ServerStatus status);

    Optional<ServerStatus> get(String slug);

    void delete(String slug);

    List<ServerStatusResponse> getAll();
}