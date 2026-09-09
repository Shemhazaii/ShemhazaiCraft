package com.shemhazaicraft.api.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findServerBySlug(String slug);

    Optional<List<Server>> findAllByEnabledTrue();
}
