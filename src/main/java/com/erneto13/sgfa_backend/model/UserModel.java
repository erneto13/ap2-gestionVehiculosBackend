package com.erneto13.sgfa_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private String role;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    private DriverModel driver;
}