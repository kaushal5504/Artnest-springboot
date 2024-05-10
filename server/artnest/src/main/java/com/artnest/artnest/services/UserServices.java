package com.artnest.artnest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.entities.User;

@Component
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public boolean getByEmail(String email){
       Optional<User> user =  userRepository.findByEmail(email);
       if(user.equals(null)){
        return true;
       }
       return false;

    }

    public List<User> getAllUsers(){
        Iterable<User> list = userRepository.findAll();
        return (List<User>) list;
    }


    
}
