package com.artnest.artnest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artnest.artnest.dao.CategoryRepository;
import com.artnest.artnest.dao.LikeRepository;
import com.artnest.artnest.dao.ProductRepository;
import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.dao.WishlistRepository;
import com.artnest.artnest.dto.ProductResponse;
import com.artnest.artnest.dto.StripeCustomerRequest;
import com.artnest.artnest.dto.StripeCustomerResponse;
import com.artnest.artnest.dto.UpdateLikesRequest;
import com.artnest.artnest.entities.Category;
import com.artnest.artnest.entities.Like;
import com.artnest.artnest.entities.Product;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.entities.Wishlist;
import com.artnest.artnest.services.ProductService;
import com.artnest.artnest.services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
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

    @Autowired
    private WishlistRepository wishlistRepository;

    @Value("${STRIPE_SECRET_KEY}")
    String stripeKey;
    


    @GetMapping
    public ResponseEntity getMethodName() {
        return  ResponseEntity.ok().build();
    }

    @CrossOrigin("*")
    @PostMapping("/update-likes")
    public ResponseEntity<?> postMethodName(@RequestBody UpdateLikesRequest req) {

        String email = req.getEmail();
        if(email.isEmpty()){
            System.out.println("empty email");
        }
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        if(product_id.equals(null)){
            System.out.println("empty product");
        }
        Optional<Product> p = productRepository.findById(product_id);
        Like like = new Like();
        Product product = p.get();
        like.setProduct(product);
        // like.setImage(product);
        like.setUser(user);
        like.setCreatedAt(LocalDateTime.now());
        System.out.println("like"+like);
        if(!p.isEmpty()){
            likeRepository.save(like);
        }else{
            System.out.println("empty");
        } 
        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin("*")
    @PostMapping("/update-dislikes")
    public ResponseEntity<?> postMethod(@RequestBody UpdateLikesRequest req) {
        String email = req.getEmail();
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        Optional<Product> p = productRepository.findById(product_id);
        Product product = p.get();
        
        Like like = likeRepository.findByProductAndUser(product, user);
        like.setProduct(null);
        like.setUser(null);
        likeRepository.deleteById(like.getId());  
        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin("*")
    @PostMapping("/isLiked")
    public ResponseEntity<?> post(@RequestBody UpdateLikesRequest req) {
        String email = req.getEmail();
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        Optional<Product> p = productRepository.findById(product_id);
        Product product = p.get();
        
        Like like = likeRepository.findByProductAndUser(product, user);
        if(like.equals(null)){
            return ResponseEntity.ok().body(false);
        }else{
            return ResponseEntity.ok().body(true);

        }
        
    }
    @CrossOrigin("*")
    @GetMapping("/countOfLikes/{product_id}")
    public ResponseEntity<?> getMethodName(@RequestParam Long product_id) {
        
        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin("*")
    @PostMapping("/addToWishlist")
    public ResponseEntity<?> postwish(@RequestBody UpdateLikesRequest req) {

        String email = req.getEmail();
        if(email.isEmpty()){
            System.out.println("empty email");
        }
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        if(product_id.equals(null)){
            System.out.println("empty product");
        }
        Optional<Product> p = productRepository.findById(product_id);
        Wishlist wishlist = new Wishlist();
        Product product = p.get();
        wishlist.setProduct(product);
        // like.setImage(product);
        wishlist.setUser(user);
        
        if(!p.isEmpty()){
            wishlistRepository.save(wishlist);
        }else{
            System.out.println("empty");
        } 
        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin("*")
    @PostMapping("/deleteFromWishlist")
    public ResponseEntity<?> postdelete(@RequestBody UpdateLikesRequest req) {
        String email = req.getEmail();
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.get();
        Long id = user.getId();
        Long product_id = req.getProduct_id();
        Optional<Product> p = productRepository.findById(product_id);
        Product product = p.get();
        
        Wishlist wishlist = wishlistRepository.findByProductAndUser(product, user);
        wishlist.setProduct(null);
        wishlist.setUser(null);
        wishlistRepository.deleteById(wishlist.getId());  
        return ResponseEntity.ok().body(null);
    }

    @CrossOrigin("*")
    @GetMapping("/getAllWishlistProducts/{user_email}")
    public ResponseEntity<?> getByCategory(@PathVariable("user_email") String user_email){
           Optional<User> u = userRepository.findByEmail(user_email);
           User user = u.get();
           Long user_id = user.getId();

           
           List<Long> productids = wishlistRepository.findAllProductsByUser(user_id);
           List<Product> list = new ArrayList<>();
           for(Long id : productids){
              Optional<Product> p = productRepository.findById(id);
              Product product = p.get();
              list.add(product);

           }
           
           
           
           return ResponseEntity.ok().body(list);
            
            

    }
    

    @CrossOrigin("*")
    @GetMapping("/getTotalPrice")
    public ResponseEntity<?> getTotalPrice(){
          Long amt = wishlistRepository.getTotalPrice();
           
           return ResponseEntity.ok().body(amt);
            
            

    }

    // @CrossOrigin("*")
    // @PostMapping("/createOrder")
    // public ResponseEntity<?> createOrder(@RequestBody Long amt) throws RazorpayException {
    //     var client = new RazorpayClient("rzp_test_4iFGoALGJA03xz","7b30lFiqt4g2N38cl2Uei5Dp");

    //     //creating json object
        // JSONObject ob = new JSONObject();
        // ob.put("amount",amt*100);
        // ob.put("currency","INR");
        // ob.put("receipt","txn_235425");

    //     //creating new order
    //     Order order = client.orders.create(ob);
    //     System.out.println(order);


        
    //     return ResponseEntity.ok().body(order);
    // }

    @CrossOrigin("*")
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Map<String,Object> data) throws RazorpayException {
        int amt = Integer.parseInt(data.get("amt").toString());
        var client = new RazorpayClient("rzp_test_4iFGoALGJA03xz","7b30lFiqt4g2N38cl2Uei5Dp");
        JSONObject ob = new JSONObject();
        ob.put("amount",amt*100);
        ob.put("currency","INR");
        ob.put("receipt","txn_235425");
        Order order = client.orders.create(ob);
        System.out.println(order);
        return order.toString();


    }
    
    


    
    


}
