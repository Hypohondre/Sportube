package itis.semestrovka.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImgService {
    String upload(MultipartFile file) throws IOException;
}
