package com.shemhazaicraft.api.modpack;

import com.shemhazaicraft.api.config.MinioProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
public class ModpackStorageServiceImpl implements ModpackStorageService{

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public ModpackStorageServiceImpl(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public void upload(String objectKey, InputStream inputStream, long size, String contentType) {

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.bucketName())
                            .object(objectKey)
                            .stream(
                                    inputStream,
                                    size,
                                    -1
                            )
                            .contentType(contentType)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to upload modpack: " + objectKey,
                    e
            );
        }

    }

    @Override
    public String generateDownloadUrl(String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperties.bucketName())
                            .object(objectKey)
                            .expiry(1, TimeUnit.HOURS)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to generate download URL: " + objectKey,
                    e
            );
        }
    }
}
