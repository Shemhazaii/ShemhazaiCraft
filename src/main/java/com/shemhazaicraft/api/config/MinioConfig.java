package com.shemhazaicraft.api.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties properties) {
        return MinioClient.builder()
                .endpoint(properties.url())
                .credentials(
                        properties.accessKey(),
                        properties.secretKey()
                )
                .build();
    }
}
