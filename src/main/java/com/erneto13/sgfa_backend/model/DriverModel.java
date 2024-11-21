package com.erneto13.sgfa_backend.model;

import lombok.Data;

@Data
public class DriverModel {
    private Integer iddrivers;
    private String name;
    private String license_number;
    private String phone;
    private String email;
    private String address;
    private Integer user_id;
}