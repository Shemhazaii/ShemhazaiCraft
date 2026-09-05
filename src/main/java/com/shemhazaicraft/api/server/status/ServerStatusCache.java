package com.shemhazaicraft.api.server.status;

import java.util.Optional;

public interface ServerStatusCache {

    void save(String slug, ServerStatus status);

    Optional<ServerStatus> get(String slug);

    void delete(String slug);
}