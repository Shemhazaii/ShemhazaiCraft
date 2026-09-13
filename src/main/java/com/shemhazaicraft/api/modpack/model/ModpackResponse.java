package com.shemhazaicraft.api.modpack.model;

import com.shemhazaicraft.api.modpack.Modpack;

public record ModpackResponse(
        Long id,
        String name,
        String description,
        String slug,
        String version,
        String minecraftVersion,
        String loader,
        String fileName,
        Long fileSize
) {

    public static ModpackResponse from(Modpack modpack) {
        return new ModpackResponse(
                modpack.getId(),
                modpack.getName(),
                modpack.getDescription(),
                modpack.getSlug(),
                modpack.getVersion(),
                modpack.getMinecraftVersion(),
                modpack.getLoader(),
                modpack.getFileName(),
                modpack.getFileSize()
        );
    }
}
