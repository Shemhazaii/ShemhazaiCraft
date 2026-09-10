package com.shemhazaicraft.api.modpack;

import java.io.InputStream;

public interface ModpackStorageService {

    void upload(
            String objectKey,
            InputStream inputStream,
            long size,
            String contentType
    );

    String generateDownloadUrl(
            String objectKey
    );

}
