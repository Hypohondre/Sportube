package itis.semestrovka.services.implementations;

import itis.semestrovka.services.interfaces.UploadImgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadingImgServiceImpl implements UploadImgService {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String upload(MultipartFile file) {
        if (file != null) {
            File photoPath = new File(uploadPath);

            if(!photoPath.exists()) photoPath.mkdir();

            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

            File photo = new File(uploadPath + "/" + filename);
            try {
                file.transferTo(photo);
            } catch (IOException e) {throw new IllegalStateException(e);}

            return filename;
        } else {
         return null;
        }
    }
}
