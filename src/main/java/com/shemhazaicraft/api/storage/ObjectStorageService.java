package com.shemhazaicraft.api.storage;

import java.io.InputStream;

public interface ObjectStorageService {

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
