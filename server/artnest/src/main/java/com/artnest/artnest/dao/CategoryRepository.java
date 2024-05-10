package com.artnest.artnest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.artnest.artnest.entities.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Category findByName(String name);
}
