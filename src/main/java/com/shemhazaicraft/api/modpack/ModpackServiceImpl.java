package com.shemhazaicraft.api.modpack;

import com.shemhazaicraft.api.modpack.model.ModpackResponse;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ModpackServiceImpl implements ModpackService{

    private final ModpackRepository modpackRepository;
    private final ModpackStorageService storageService;

    public ModpackServiceImpl(ModpackRepository modpackRepository, ModpackStorageService storageService) {
        this.modpackRepository = modpackRepository;
        this.storageService = storageService;
    }

    @Override
    public ModpackResponse upload(String name, String version, String minecraftVersion, String loader, MultipartFile file) {
        String slug = name
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-");

        String objectKey =
                "modpacks/"
                        + slug + "/"
                        + version + "/"
                        + file.getOriginalFilename();

        try {
            storageService.upload(
                    objectKey,
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Modpack modpack = new Modpack();

        modpack.setName(name);
        modpack.setSlug(slug);
        modpack.setVersion(version);
        modpack.setMinecraftVersion(minecraftVersion);
        modpack.setLoader(loader);
        modpack.setFileName(file.getOriginalFilename());
        modpack.setObjectKey(objectKey);
        modpack.setFileSize(file.getSize());

        modpackRepository.save(modpack);

        return ModpackResponse.from(modpack);
    }

    @Override
    public String generateDownloadUrl(Long id) {
        Modpack modpack = modpackRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Modpack not found"
                        )
                );

        return storageService.generateDownloadUrl(
                modpack.getObjectKey()
        );
    }
}
