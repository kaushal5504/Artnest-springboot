package com.artnest.artnest.dao;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artnest.artnest.entities.Like;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.entities.User;

@Repository
public interface LikeRepository extends CrudRepository<Like,Long> {
    public Like findByProductAndUser(Product p, User u);
    
    @Query(value = "select userid from like_info where product_id =?",nativeQuery = true)
    public List<Long> findAllUsersByProduct(Long id);
    //public Long findCountByProduct(Long id);
}
