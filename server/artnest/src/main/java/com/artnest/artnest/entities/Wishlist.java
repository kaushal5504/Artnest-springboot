package com.artnest.artnest.entities;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="wishlist_info")
public class Wishlist {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="product_id")
    @JsonManagedReference
    private Product product;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonManagedReference
    private User user;


    public Wishlist() {
    }

    public Wishlist(long id, Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wishlist id(long id) {
        setId(id);
        return this;
    }

    public Wishlist product(Product product) {
        setProduct(product);
        return this;
    }

    public Wishlist user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wishlist)) {
            return false;
        }
        Wishlist wishlist = (Wishlist) o;
        return id == wishlist.id && Objects.equals(product, wishlist.product) && Objects.equals(user, wishlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }
    



    
}
