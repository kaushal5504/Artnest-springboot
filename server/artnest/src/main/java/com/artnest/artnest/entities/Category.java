package com.artnest.artnest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name="category_info")

public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    @JsonBackReference
    private List<Product> imageData = new ArrayList<>();



    public Category() {
    }

    public Category(Long id, String name, List<Product> imageData) {
        this.id = id;
        this.name = name;
        this.imageData = imageData;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getImageData() {
        return this.imageData;
    }

    public void setImageData(List<Product> imageData) {
        this.imageData = imageData;
    }

    public Category id(Long id) {
        setId(id);
        return this;
    }

    public Category name(String name) {
        setName(name);
        return this;
    }

    public Category imageData(List<Product> imageData) {
        setImageData(imageData);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(imageData, category.imageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageData);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" 
           ;
    }
    
}
