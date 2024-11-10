package com.erneto13.sgfa_backend.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    String token;
    String refreshToken;
}
