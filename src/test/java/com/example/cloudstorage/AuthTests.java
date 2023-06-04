package com.example.cloudstorage;

import com.example.cloudstorage.dto.JwtRequest;
import com.example.cloudstorage.jwt.JwtProvider;
import com.example.cloudstorage.service.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthTests {
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private AuthenticationManager authenticationManager;

    private final String userName = "user";
    private final String password = "123";
    @InjectMocks
    private AuthServiceImpl authService;

    private final JwtRequest authRequest = new JwtRequest(userName, password);

    @Test
    void loginTest() {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        String token = UUID.randomUUID().toString();
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        given(jwtProvider.generateAccessToken(auth)).willReturn(token);
        assertEquals(token, authService.login(authRequest).getAccessToken());

    }
}
