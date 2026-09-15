package com.shemhazaicraft.api.modpack;


import com.shemhazaicraft.api.modpack.model.ModpackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/modpacks")
@RequiredArgsConstructor
public class ModpackController {

    private final ModpackService modpackService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ModpackResponse> upload(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String version,
            @RequestParam String minecraftVersion,
            @RequestParam String loader,
            @RequestParam("file") MultipartFile file,
            @RequestParam("thumbnail") MultipartFile thumbnail
    ) {
        ModpackResponse response = modpackService.upload(
                name,
                description,
                version,
                minecraftVersion,
                loader,
                file,
                thumbnail
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ModpackResponse>> getLatest() {
        return ResponseEntity.ok(modpackService.getLatest());
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Void> download(
            @PathVariable Long id
    ) {
        String url = modpackService.generateDownloadUrl(id);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
