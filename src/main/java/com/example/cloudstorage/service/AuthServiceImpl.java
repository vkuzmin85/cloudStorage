package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.JwtRequest;
import com.example.cloudstorage.dto.JwtResponse;
import com.example.cloudstorage.exception.AuthException;
import com.example.cloudstorage.jwt.JwtProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final Map<String, String> tokenStorage = new HashMap<>();

    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        log.info("Login : " + authRequest.getLogin());
        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateAccessToken(authentication);
        tokenStorage.put(token, authRequest.getLogin());
        return new JwtResponse(true, token);
    }

    public void logout(String authToken) {
        log.info("Logout: " + jwtProvider.getUsernameFromToken(authToken));
        tokenStorage.remove(authToken);
    }

}