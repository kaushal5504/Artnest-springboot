package com.artnest.artnest.services;


import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artnest.artnest.dto.CreateProductRequest;
import com.artnest.artnest.dto.ProductWithImage;
import com.artnest.artnest.entities.Product;

import io.jsonwebtoken.io.IOException;

@Service
@Component
public interface ProductService {

    public Product createProduct(CreateProductRequest req, MultipartFile file)throws java.io.IOException;
    public Product createProductN(CreateProductRequest req);

    public String deleteProduct(Long productId);

    public Product updateProduct(Long productId, Product product);

    public Product findProductById(Long id);

    public ResponseEntity<?> findProductByTitle(String name)throws java.io.IOException;

    public List<Product> findProductByCategory(String category);

    public ResponseEntity<?> getAllProducts() throws java.io.IOException;
    
    


}
