package com.artnest.artnest.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artnest.artnest.dto.CreateProductRequest;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.services.ProductService;

@RequestMapping("api/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public String test(){
        return " working ";
    }

    
    
}
