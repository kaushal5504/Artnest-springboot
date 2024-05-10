package com.artnest.artnest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.services.UserService;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
public class UserServiceImpl  implements UserService{
     @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return this.userRepository.save(user);
    }

    public User findByEmailAndPassword(String email, String password){
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    

    public UserDetailsService userDetailsService(){

        return  new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {

                return userRepository.findByEmail(username)
                            .orElseThrow(()-> new UsernameNotFoundException("User not found with email" + username));
                
            }
            
        };
    }

    @Override
    public User findUserNameByJwt(String jwt) throws JwtException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserNameByJwt'");
    }

   
}
