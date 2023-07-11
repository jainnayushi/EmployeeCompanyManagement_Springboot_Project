//package com.ayushi.Task1.extra;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//@Component
//public
//class ImageService {
//    public final String UPLOAD_DIR = "C:\\Users\\Ayushi.Jain\\IdeaProjects\\Task1\\src\\main\\resources\\static\\image";
//
//    public boolean uploadFile(MultipartFile file) {
//        boolean f1 = false;
//        try {
//            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//            f1 = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return f1;
//    }
//
//    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
//        Path filePath = Paths.get(UPLOAD_DIR + File.separator).resolve(fileName).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//        System.err.println(resource);
//        return resource;
//    }
//
//}
