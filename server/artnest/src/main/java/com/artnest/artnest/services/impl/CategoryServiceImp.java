package com.artnest.artnest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.artnest.artnest.dao.CategoryRepository;
import com.artnest.artnest.dto.CreateCategoryRequest;
import com.artnest.artnest.entities.Category;
import com.artnest.artnest.services.CategoryService;

@Service
@Component
public class CategoryServiceImp {
    
    @Autowired
    private CategoryRepository createRepository;
}
