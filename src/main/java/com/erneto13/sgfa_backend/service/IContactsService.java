package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.ContactModel;

import java.util.List;

public interface IContactsService {
    public List<ContactModel> findAll();
}
