//package com.ayushi.Task1.extra;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//
//@RestController
//public class ImageUploadController {
//    @Autowired
//    private ImageService imageService;
//
//    @Autowired
//    private ImageRepository imageRepo;
//
//    @PostMapping("/image")
//    public ResponseEntity<String> uploadImage(@RequestParam("keyy") MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must Contain File");
//            }
//            if (!file.getContentType().equals("image/png")) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only PNG Content type allowed");
//            }
//            // File Upload In Directory Code
//            boolean uploadStatus = imageService.uploadFile(file);
//            if (uploadStatus) {
//                // File Upload In DB Code
//                Image img = new Image();
//                img.setImage(file.getBytes());
//                img.setData(file.getOriginalFilename());
//                imageRepo.save(img);
//                return ResponseEntity.ok("File Uploaded Successfully " + file.getOriginalFilename());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again!");
//    }
//
//    @RequestMapping(value = "/getImageFromDB/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
//        byte[] imgDB = imageRepo.findById(id).get().getImage();
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png")).body(imgDB);
//    }
//
//    @GetMapping("/getImageFromDirectory/{fileName}")
//    public ResponseEntity<Resource> func(@PathVariable String fileName) throws MalformedURLException {
//        Resource r = imageService.loadFileAsResource(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png")).body(r);
//    }
//
//    @PutMapping("/image/{id}")
//    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam("keyy") MultipartFile image) throws IOException {
//        Image imgDB = imageRepo.findById(Math.toIntExact(id)).get();
//        imgDB.setImage(image.getBytes());
//        imgDB.setData(image.getOriginalFilename());
//        imageRepo.save(imgDB);
//        return ResponseEntity.ok("Image Updated Successfully ");
//    }
//
//    @PutMapping("/imageTest/{id}")
//    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestParam("keyy") MultipartFile image, @RequestParam("data") String data) throws IOException {
//        Image imgDB = imageRepo.findById(Math.toIntExact(id)).get();
//        imgDB.setImage(image.getBytes());
//        imgDB.setData(data);
//        imageRepo.save(imgDB);
//        return ResponseEntity.ok("Image Updated Successfully ");
//    }
//
////    @PostMapping("/imageTest")
////    public ResponseEntity<String> uploadImage(@RequestParam("keyy") MultipartFile file, @RequestParam("data") String data) throws IOException {
////                Image img = new Image();
////                img.setImage(file.getBytes());
////                img.setData(data);
////                imageRepo.save(img);
////                return ResponseEntity.ok("File Uploaded Successfully " + file.getOriginalFilename());
////    }
//
//}
