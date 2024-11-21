package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.ContactModel;

import java.util.List;

public interface IContactsResository {
    public List<ContactModel> findAll();
}
