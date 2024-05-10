package com.artnest.artnest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.artnest.artnest.entities.User;

import io.jsonwebtoken.JwtException;



@Component
public interface UserService {
   

    public User addUser(User user);

    public User findByEmailAndPassword(String email, String password);

    public User findUserNameByJwt(String jwt) throws JwtException;
    

    public UserDetailsService userDetailsService();
}


