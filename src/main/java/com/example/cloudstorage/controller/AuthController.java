package com.example.cloudstorage.controller;

import com.example.cloudstorage.dto.JwtRequest;
import com.example.cloudstorage.dto.JwtResponse;
import com.example.cloudstorage.exception.AuthException;
import com.example.cloudstorage.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(new JwtResponse(true, token.getAccessToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {
        authService.logout(token);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
