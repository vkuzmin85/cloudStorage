package com.example.cloudstorage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class JwtResponse {

    boolean success;
    @JsonProperty("auth-token")
    private String accessToken;
}