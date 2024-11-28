package com.erneto13.sgfa_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "drivers")
public class DriverModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driver_id;

    @Column
    private String name;

    @Column
    private String license_number;

    @Column
    private String license_category;

    @Column
    private String phone;

    @Column
    private String address;
}