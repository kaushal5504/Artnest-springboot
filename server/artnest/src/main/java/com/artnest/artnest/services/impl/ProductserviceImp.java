package com.artnest.artnest.services.impl;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;

import com.artnest.artnest.dao.CategoryRepository;
import com.artnest.artnest.dao.ProductRepository;
import com.artnest.artnest.dto.CreateProductRequest;
import com.artnest.artnest.dto.ProductWithImage;
import com.artnest.artnest.entities.Category;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.services.CloudinaryService;
import com.artnest.artnest.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.ByteArrayResource;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.*;

@Service
@Component
public class ProductserviceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // @Autowired
    // private FileService fileService;

    @Autowired
    private CloudinaryService cloudinaryService;

    

    //private final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
    //private final String UPLOAD_DIR = System.getProperty("user.dir") + "\\" + "src" + "\\" + "main" + "\\" + "resources" + "\\" +"static"+"\\"+ "images";
    //C:\\Users\\hp\\Desktop\\Development\\React+SpringBoot\\server\\New folder\\artnest\\target\\classes\\static\\imagesGandhiji.png

    public ProductserviceImp() throws java.io.IOException {
    }

    @Override
    public Product createProduct(CreateProductRequest req, MultipartFile file) throws java.io.IOException {
        Map data = cloudinaryService.upload(file);

       
        String fileName = file.getOriginalFilename();
        String imagePath = (String) data.get("url");

        Category c = categoryRepository.findByName(req.getCategory());

        if (c == null) {
            Category cn = new Category();
            cn.setName(req.getCategory());

            c = categoryRepository.save(cn);

        }

        Product p = new Product();
        p.setCategory(c);
        p.setDescription(req.getDescription());
        p.setDiscountPresent(req.getDiscountPresent());
        p.setDiscountedPrice(req.getDiscountedPrice());
        p.setFilePath(imagePath);
        p.setTitle(req.getTitle());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());

        return productRepository.save(p);

    }
    @Override
    public Product createProductN(CreateProductRequest req) {
        Category c = categoryRepository.findByName(req.getCategory());

        if (c == null) {
            Category cn = new Category();
            cn.setName(req.getCategory());

            c = categoryRepository.save(cn);

        }

        Product p = new Product();
        p.setCategory(c);
        p.setDescription(req.getDescription());
        p.setDiscountPresent(req.getDiscountPresent());
        p.setDiscountedPrice(req.getDiscountedPrice());
        p.setFilePath(req.getFilePath());
        p.setTitle(req.getTitle());
        p.setPrice(req.getPrice());
        p.setQuantity(req.getQuantity());

        return productRepository.save(p);
    }


    @Override
    public String deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return "deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public Product findProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductById'");
    }

    @Override
    public ResponseEntity<Object> findProductByTitle(String name) throws java.io.IOException {
        Product product = productRepository.findByTitle(name);
        
        // Path path = Paths.get(product.getFilePath());
        // byte[] file = Files.readAllBytes(path);

        // ObjectMapper mapper = new ObjectMapper();
        // String productJson = mapper.writeValueAsString(product);

        // MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        // // Prepare JSON data with appropriate headers
        // HttpHeaders jsonHeaders = new HttpHeaders();
        // jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        // formData.add("jsonData", new HttpEntity<>(productJson, jsonHeaders));

        // // Prepare file data with appropriate headers (adjust content type based on your
        // // file)
        // HttpHeaders fileHeaders = new HttpHeaders();
        // fileHeaders.setContentType(MediaType.IMAGE_PNG); // Adjust for image/jpeg, etc.
        // formData.add("fileData", new HttpEntity<>(new ByteArrayResource(file), fileHeaders));

        

        // return ResponseEntity.status(HttpStatus.valueOf(200))
        //          .contentType(MediaType.MULTIPART_FORM_DATA)
        //          .body(formData);

        return ResponseEntity.ok().body(product);

    }

    @Override
    public List<Product> findProductByCategory(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductByCategory'");
    }

    @Override
    public ResponseEntity<?> getAllProducts() throws java.io.IOException {
        List<Product> list = (List<Product>) productRepository.findAll();
       
        // MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        // ObjectMapper mapper = new ObjectMapper();

        // for (Product product : list) {
        //     String productJson = mapper.writeValueAsString(product);
    
        //     // Prepare JSON data with appropriate headers
        //     HttpHeaders jsonHeaders = new HttpHeaders();
        //     jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        //     formData.add("jsonData", new HttpEntity<>(productJson, jsonHeaders));
    
        //     // Prepare file data with appropriate headers (adjust content type based on your file)
        //     Path path = Paths.get(product.getFilePath());
        //     byte[] file = Files.readAllBytes(path);
        //     HttpHeaders fileHeaders = new HttpHeaders();
        //     fileHeaders.setContentType(MediaType.IMAGE_PNG); // Adjust for image/jpeg, etc.
        //     formData.add("fileData", new HttpEntity<>(new ByteArrayResource(file), fileHeaders));
        // }
        
        return ResponseEntity.ok().body(list);  
    }

    
}
