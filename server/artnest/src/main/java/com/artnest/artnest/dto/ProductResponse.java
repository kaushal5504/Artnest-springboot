package com.artnest.artnest.dto;

import com.artnest.artnest.entities.Category;
import com.artnest.artnest.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPresent;
    private int quantity;
    
    private String filePath;
    private Category category;
    
    private List<Long> usersLiked;
    
}
