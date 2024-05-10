package com.artnest.artnest.services.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artnest.artnest.services.CloudinaryService;
import com.cloudinary.Cloudinary;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public Map upload(MultipartFile file)  {
        try{
            Map  data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
          return data;

        }catch(Exception e){
            e.printStackTrace();
            
        }
        return null;
        
        
    }
    
}
