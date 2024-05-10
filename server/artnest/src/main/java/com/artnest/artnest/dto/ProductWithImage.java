package com.artnest.artnest.dto;

import org.springframework.http.ResponseEntity;

import com.artnest.artnest.entities.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.hibernate.mapping.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithImage {

    private Product product;
    private byte[] file;

    public MultiValueMap createMultipartResponse(Product product, byte[] file)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String productJson = mapper.writeValueAsString(product);

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
      
        // Prepare JSON data with appropriate headers
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        formData.add("jsonData", new HttpEntity<>(productJson, jsonHeaders));
      
        // Prepare file data with appropriate headers (adjust content type based on your file)
        HttpHeaders fileHeaders = new HttpHeaders();
        fileHeaders.setContentType(MediaType.IMAGE_PNG); // Adjust for image/jpeg, etc.
        formData.add("fileData", new HttpEntity<>(new ByteArrayResource(file), fileHeaders));

        MultiValueMap<String, Object> res = new LinkedMultiValueMap<>();
        res.add("contentType",MediaType.MULTIPART_FORM_DATA);
        res.add("body",formData);
      
        // Return response with multipart content type
        // return ResponseEntity.status(HttpStatus.valueOf(200))
        //         .contentType(MediaType.MULTIPART_FORM_DATA)
        //         .body(formData);

        return res;
      }
    
}
