package com.shemhazaicraft.api.modpack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModpackRepository extends JpaRepository<Modpack, Long> {


    @Query("SELECT m FROM Modpack m WHERE m.id IN " +
            "(SELECT MAX(m2.id) FROM Modpack m2 GROUP BY m2.slug) " +
            "ORDER BY m.createdAt DESC")
    List<Modpack> findLatestModpacksGroupBySlug();
}
