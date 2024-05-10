package com.artnest.artnest.services;

import com.artnest.artnest.dto.JwtAuthResponse;
import com.artnest.artnest.dto.RefreshTokenRequest;
import com.artnest.artnest.dto.SignInRequest;
import com.artnest.artnest.dto.SignUpRequest;
import com.artnest.artnest.entities.User;

public interface AuthenticationService {
    public User signup(SignUpRequest signUpRequest);

    public JwtAuthResponse signIn(SignInRequest signInRequest);

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
