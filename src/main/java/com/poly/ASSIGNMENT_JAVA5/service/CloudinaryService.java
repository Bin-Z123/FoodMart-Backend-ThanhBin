package com.poly.ASSIGNMENT_JAVA5.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CloudinaryService {
    Cloudinary cloudinary;
    public void uploadFile(MultipartFile file){
        try{
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null){
                throw new RuntimeException("File name is null");
            }
            String fileNameWithoutExtension = originalFilename.substring(0,originalFilename.lastIndexOf("."));
            cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder","asm-java5",
                    "public_id",fileNameWithoutExtension
            ));
//            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.asMap("folder","asm-java5"));
//            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Upload Failed",e);
        }
    }
}
