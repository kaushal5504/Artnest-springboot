package com.artnest.artnest.controllers;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artnest.artnest.dao.CategoryRepository;
import com.artnest.artnest.dao.LikeRepository;
import com.artnest.artnest.dao.ProductRepository;
import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.dto.CreateProductRequest;
import com.artnest.artnest.dto.ProductResponse;
import com.artnest.artnest.dto.UpdateLikesRequest;
import com.artnest.artnest.entities.Category;
import com.artnest.artnest.entities.Like;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.services.*;
import org.slf4j.Logger; // Replace with your specific logging library
import java.time.LocalDateTime;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/all")
@RequiredArgsConstructor
public class AllAccess {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LikeRepository likeRepository;

    @CrossOrigin("*")
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() throws IOException {
           
           return productService.getAllProducts();
            
            

    }

    @CrossOrigin("*")
    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable("category") String category){
           Category c = categoryRepository.findByName(category);
           
           List<Product> list = productRepository.findAllByCategoryId(c.getId());
           List<ProductResponse> listToSend =  new ArrayList<>();
           for(Product p : list){
               List<Long> usersId = likeRepository.findAllUsersByProduct(p.getId());
               ProductResponse pr = new ProductResponse();
               pr.setCategory(p.getCategory());
               pr.setDescription(p.getDescription());
               pr.setFilePath(p.getFilePath());
               pr.setDiscountPresent(p.getDiscountPresent());
               pr.setDiscountedPrice(p.getDiscountedPrice());
               pr.setId(p.getId());
               pr.setPrice(p.getPrice());
               pr.setQuantity(p.getQuantity());
               pr.setTitle(p.getTitle());
               pr.setUsersLiked(usersId);
               listToSend.add(pr);
           }
           System.out.println(list);
           return ResponseEntity.ok().body(listToSend);
            
            

    }

    @CrossOrigin("*")
    @GetMapping("/getUserId/{email}")
    public ResponseEntity<?> getUserId(@PathVariable("email") String email){
           Optional<User> u= userRepository.findByEmail(email);
           User user = u.get();
           return ResponseEntity.ok().body(user.getId());
            
            

    }
    
    
    @CrossOrigin("*")
    @GetMapping("/findProductByTitle/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title)  throws java.io.IOException{
        return productService.findProductByTitle(title);

    }

    @CrossOrigin("*")
    @PostMapping("/update-likes")
    public ResponseEntity<?> postMethodName(@RequestBody UpdateLikesRequest req) {
        String email = req.getEmail();
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        Optional<Product> p = productRepository.findById(product_id);
        Like like = new Like();
        Product product = p.get();
        // like.setImage(product);
        like.setUser(user);
        like.setCreatedAt(LocalDateTime.now());





        
        return ResponseEntity.ok().body(null);
    }
    


    
}
