package com.erneto13.sgfa_backend.model;

import lombok.Data;

@Data
public class UserModel {
    Integer user_id;
    String username;
    String password;
    String email;
    String role;
}
