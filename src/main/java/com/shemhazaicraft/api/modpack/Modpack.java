package com.shemhazaicraft.api.modpack;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "modpacks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Modpack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    private String version;

    private String minecraftVersion;

    private String loader;

    private String fileName;

    private String objectKey;

    private Long fileSize;

    private Instant createdAt;

}
