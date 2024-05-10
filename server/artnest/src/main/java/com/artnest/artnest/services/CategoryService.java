package com.artnest.artnest.services;

import org.springframework.stereotype.Component;

import com.artnest.artnest.entities.Category;

@Component
public interface CategoryService {

    public Category addCategory(String name);

    
}
