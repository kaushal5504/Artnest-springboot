package com.artnest.artnest.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import java.util.*;


@Configuration
public class ProjectConfig {


    
   @Bean
    public  Cloudinary getCloudinary(){

        Map config = new HashMap();
        config.put("cloud_name","dppkkbval");
        config.put("api_key","389571758128797");
        config.put("api_secret","SLhlQzWOnGY98pkzguNL6lrlonw");
        config.put("secure",true);
        return new Cloudinary(config);

    }
    
}
