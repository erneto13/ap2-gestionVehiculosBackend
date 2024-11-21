package com.erneto13.sgfa_backend.model;

import lombok.Data;

@Data
public class UserModel {
    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private String role;
}