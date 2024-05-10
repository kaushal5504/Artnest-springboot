package com.artnest.artnest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artnest.artnest.entities.Like;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.entities.Wishlist;

import java.util.*;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist,Long> {
   public Wishlist findByProductAndUser(Product p, User u);
    
    @Query(value = "select userid from wishlist_info where product_id =?",nativeQuery = true)
    public List<Long> findAllUsersByProduct(Long id);
    
    @Query(value = "select product_id from wishlist_info where userid =?",nativeQuery = true)
    public List<Long> findAllProductsByUser(Long id);

    @Query(value = "select sum(price) from product_info as p inner join wishlist_info as w on p.id = w.product_id",nativeQuery = true)
    public Long getTotalPrice();
}