package com.example.cloudstorage.service;

import com.example.cloudstorage.dto.JwtRequest;
import com.example.cloudstorage.dto.JwtResponse;
import lombok.NonNull;

public interface AuthService {
    public JwtResponse login(@NonNull JwtRequest authRequest);
    public void logout(String authToken);
}
