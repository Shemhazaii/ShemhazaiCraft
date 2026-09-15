package com.shemhazaicraft.api.modpack;

import com.shemhazaicraft.api.modpack.model.ModpackResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ModpackService {
    ModpackResponse upload(String name, String description, String version, String minecraftVersion, String loader, MultipartFile file, MultipartFile thumbnail);

    String generateDownloadUrl(Long id);

    List<ModpackResponse> getLatest();
}
