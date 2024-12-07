package com.erneto13.sgfa_backend.dto;

import lombok.Data;

@Data
public class CreateCredentialsDto {
    private Long driverId;
    private String email;
    private String password;
    private String role;
}
