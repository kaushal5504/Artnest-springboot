package com.artnest.artnest.controllers;

import java.sql.Ref;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artnest.artnest.dto.RefreshTokenRequest;
import com.artnest.artnest.dto.SignInRequest;
import com.artnest.artnest.dto.SignUpRequest;
import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.services.AuthenticationService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;
    

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignUpRequest  signUpRequest,BindingResult bindingResult){
        User user =null;
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
           
            return ResponseEntity.badRequest().body(errors);
        }
        try{
             user = authenticationService.signup(signUpRequest);
             return ResponseEntity.ok(user);

        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @CrossOrigin(origins = "http://localhost:3000") 
       @PostMapping("/signin")
       public ResponseEntity<Object> signin(@RequestBody SignInRequest signInRequest){

        try{
            var jwtAuthResponse = authenticationService.signIn(signInRequest);
            // String email = signInRequest.getEmail();
            // Optional<User> u = userRepository.findByEmail(email);
            // User user = u.get();
            return ResponseEntity.ok(jwtAuthResponse);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }
    @CrossOrigin(origins = "http://localhost:3000")
     @PostMapping("/refreshtoken")
       public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

        try{
            var jwtAuthResponse = authenticationService.refreshToken(refreshTokenRequest);
            return ResponseEntity.ok(jwtAuthResponse);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    
} 
       
        
        


