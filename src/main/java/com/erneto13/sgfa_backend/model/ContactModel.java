package com.erneto13.sgfa_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
public class ContactModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long contact_id;
    String name;
    String company;
    String phone;
    String email;
    String service;
    String status;
}
