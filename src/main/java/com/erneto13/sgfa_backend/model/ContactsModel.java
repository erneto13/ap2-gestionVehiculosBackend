package com.erneto13.sgfa_backend.model;

import lombok.Data;

@Data
public class ContactsModel {
    Integer idcontact;
    String name;
    String company;
    String phone;
    String email;
    String service;
    String status;
}
