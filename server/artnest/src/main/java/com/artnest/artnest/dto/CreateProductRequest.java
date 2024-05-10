package com.artnest.artnest.dto;

import javax.mail.Multipart;
import javax.validation.constraints.Email;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;


public class CreateProductRequest {

    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPresent;
    private int quantity;
    private String filePath;
    private String category;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String title, String description, int price, int discountedPrice, int discountPresent, int quantity, String filePath, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.discountPresent = discountPresent;
        this.quantity = quantity;
        this.filePath = filePath;
        this.category = category;
    }
    public CreateProductRequest(@JsonProperty("title") String title, 
                      @JsonProperty("description") String description, 
                      @JsonProperty("price") int price,
                      @JsonProperty("discountedPrice") int discountedPrice,
                      @JsonProperty("discountPresent") int discountPresent,
                      @JsonProperty("filePath") String filePath,
                      @JsonProperty("quantity") int quantity,
                      @JsonProperty("category") String category) {
                        this.title = title;
                        this.description = description;
                        this.price = price;
                        this.discountedPrice = discountedPrice;
                        this.discountPresent = discountPresent;
                        this.quantity = quantity;
                        this.filePath = filePath;
                        this.category = category;
        }
    

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPrice() {
        return this.discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscountPresent() {
        return this.discountPresent;
    }

    public void setDiscountPresent(int discountPresent) {
        this.discountPresent = discountPresent;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CreateProductRequest title(String title) {
        setTitle(title);
        return this;
    }

    public CreateProductRequest description(String description) {
        setDescription(description);
        return this;
    }

    public CreateProductRequest price(int price) {
        setPrice(price);
        return this;
    }

    public CreateProductRequest discountedPrice(int discountedPrice) {
        setDiscountedPrice(discountedPrice);
        return this;
    }

    public CreateProductRequest discountPresent(int discountPresent) {
        setDiscountPresent(discountPresent);
        return this;
    }

    public CreateProductRequest quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public CreateProductRequest filePath(String filePath) {
        setFilePath(filePath);
        return this;
    }

    public CreateProductRequest category(String category) {
        setCategory(category);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CreateProductRequest)) {
            return false;
        }
        CreateProductRequest createProductRequest = (CreateProductRequest) o;
        return Objects.equals(title, createProductRequest.title) && Objects.equals(description, createProductRequest.description) && price == createProductRequest.price && discountedPrice == createProductRequest.discountedPrice && discountPresent == createProductRequest.discountPresent && quantity == createProductRequest.quantity && Objects.equals(filePath, createProductRequest.filePath) && Objects.equals(category, createProductRequest.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, discountedPrice, discountPresent, quantity, filePath, category);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            ", discountedPrice='" + getDiscountedPrice() + "'" +
            ", discountPresent='" + getDiscountPresent() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }

    
    


    
}
