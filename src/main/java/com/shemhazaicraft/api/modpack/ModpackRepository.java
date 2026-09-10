package com.shemhazaicraft.api.modpack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModpackRepository extends JpaRepository<Modpack, Long> {
}
