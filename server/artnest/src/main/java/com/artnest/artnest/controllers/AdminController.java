package com.artnest.artnest.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artnest.artnest.dao.ProductRepository;
import com.artnest.artnest.dto.CreateProductRequest;
import com.artnest.artnest.dto.ProductWithImage;
import com.artnest.artnest.entities.Category;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.services.*;
import com.artnest.artnest.services.impl.FileService;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.*; // For @RequestMapping, @RequestMethod, @Produces, and @Consumes
import java.lang.Iterable; // If


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    
    @CrossOrigin("http://localhost:3000")
    @PostMapping("/addProduct")
    public ResponseEntity<?> createProduct(@RequestPart("productDetails") CreateProductRequest createProductRequest, @RequestPart("file") MultipartFile file) throws IOException{

        if (file.getContentType() == null || file.getContentType().isEmpty()) {
            System.out.println("empty");
        }
        
        Product product = productService.createProduct(createProductRequest,file);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @CrossOrigin("*")
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get(); // Safe to access properties here
            System.out.println(updatedProduct);
            Category c = updatedProduct.getCategory();
            Long category_id = c.getId();
            List<Product> list = productRepository.findAllByCategoryId(category_id);
            if(!list.isEmpty()){
                updatedProduct.setCategory(null);
            }
            productRepository.deleteById(id);
            

        } else {
           
        }
        //productRepository.deleteProductById(id);;
        return ResponseEntity.ok().body(null);
        
    }
    
    
    @DeleteMapping("/deleteAllProducts")
    public String deleteAllProducts(){

        productRepository.deleteAll();
        return "deletedSuccessFully";

    }
    
    @GetMapping("/findProductByTitle/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title) throws java.io.IOException{
        return productService.findProductByTitle(title);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get(); // Safe to access properties here
    
            updatedProduct.setDiscountPresent(product.getDiscountPresent());
            updatedProduct.setDiscountedPrice(product.getDiscountedPrice());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.setFilePath(product.getFilePath());
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setTitle(product.getTitle()); // Update title only if allowed (see discussion below)
    
            productRepository.save(updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            // Handle case where product with the given id is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or return a custom error message
        }
    }


    // @PostMapping(path = "/upload")
    // public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
    //         StringBuilder stringBuilder = new StringBuilder();
    //     try {
    //      FileData fileData =  fileService.uploadFile(file);
    //      stringBuilder.append("File Uploaded successfully")
    //              .append(fileData.getFileName());
    //     }catch (Exception e){
    //     stringBuilder.append(e.getMessage());
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());
    // }
    
    @CrossOrigin("*")
    @PostMapping("/createProduct")
    public ResponseEntity<?>  postMethodName(@RequestBody CreateProductRequest entity) {
        Product product = productService.createProductN(entity);
        return ResponseEntity.ok().body(product);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/updateProductQuantity/{title}")
    public ResponseEntity<?> putMethodName(@PathVariable String title, @RequestBody int qty) {
        Product product = productRepository.findByTitle(title);
        product.setQuantity(qty);

        
        return ResponseEntity.ok().body(product) ;
    }
    
    
    

    
}
