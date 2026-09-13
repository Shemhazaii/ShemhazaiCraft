package com.shemhazaicraft.api.utils;

import org.springframework.http.MediaTypeFactory;
import org.springframework.web.multipart.MultipartFile;

public final class FileUploadUtils {

    private FileUploadUtils() {

    }

    public static String getContentType(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "application/octet-stream";
        }

        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();

        // Cek jika header dari browser kosong atau generic
        if (contentType == null || contentType.equalsIgnoreCase("application/octet-stream")) {
            if (filename != null) {
                contentType = MediaTypeFactory.getMediaType(filename)
                        .map(Object::toString)
                        .orElse("image/jpeg"); // default fallback jika gagal terdeteksi
            } else {
                contentType = "image/jpeg";
            }
        }

        return contentType;
    }

}
