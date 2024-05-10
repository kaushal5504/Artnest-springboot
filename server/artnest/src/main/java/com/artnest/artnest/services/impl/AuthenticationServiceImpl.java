package com.artnest.artnest.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artnest.artnest.dao.UserRepository;
import com.artnest.artnest.dto.JwtAuthResponse;
import com.artnest.artnest.dto.RefreshTokenRequest;
import com.artnest.artnest.dto.SignInRequest;
import com.artnest.artnest.dto.SignUpRequest;
import com.artnest.artnest.entities.Role;
import com.artnest.artnest.entities.User;
import com.artnest.artnest.services.AuthenticationService;
import com.artnest.artnest.services.JWTService;

import lombok.RequiredArgsConstructor;









@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    public User signup(SignUpRequest signUpRequest){
        User user = new User();
       
        user.setName(signUpRequest.getName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());

        return userRepository.save(user);
    }

    public JwtAuthResponse signIn(SignInRequest signInRequest){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));

        User user  = this.userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);

        var refreshjwtToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshjwtToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){

        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new IllegalArgumentException("User not found"));

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwtToken = jwtService.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshTokenRequest.getToken());

        return jwtAuthResponse;
        }

        return null;

    }

    
    

}
