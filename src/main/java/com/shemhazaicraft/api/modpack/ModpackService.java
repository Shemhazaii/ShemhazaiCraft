package com.shemhazaicraft.api.modpack;

import com.shemhazaicraft.api.modpack.model.ModpackResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ModpackService {
    ModpackResponse upload(String name, String version, String minecraftVersion, String loader, MultipartFile file);

    String generateDownloadUrl(Long id);
}
