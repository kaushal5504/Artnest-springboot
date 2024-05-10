package com.artnest.artnest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artnest.artnest.services.CloudinaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;

@RestController
@RequestMapping("/cloudinary/upload")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestParam("image") MultipartFile file) {

        Map data = this.cloudinaryService.upload(file);
        return ResponseEntity.ok().body(data);
        
    }
    


    
}
