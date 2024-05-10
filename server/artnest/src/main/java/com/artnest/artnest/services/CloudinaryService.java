package com.artnest.artnest.services;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService{

    public Map upload(MultipartFile file);
}


