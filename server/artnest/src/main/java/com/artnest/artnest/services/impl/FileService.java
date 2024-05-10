package com.artnest.artnest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.artnest.artnest.dao.FileRepo;
//import com.artnest.artnest.entities.FileData;
import com.artnest.artnest.utils.FileUtil;

import java.io.File;
import java.io.IOException;

// @Component
// @Service
public class FileService {
    
    
    // private final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();


    // public FileService() throws IOException {

    // }


    // public String uploadFile(MultipartFile file) throws IOException{
    //     String filePath = UPLOAD_DIR+file.getOriginalFilename();
    //     file.transferTo(new File(filePath));
    //     return filePath;
    // }

    // public  byte[] downloadFile(String fileName){
    //     FileData fileData =  repo.findByFileName(fileName);
    //     return  FileUtil.decompressFile(fileData.getData());
    //  }
    
}
