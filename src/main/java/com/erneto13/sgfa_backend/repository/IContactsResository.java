package com.erneto13.sgfa_backend.repository;

import com.erneto13.sgfa_backend.model.ContactsModel;

import java.util.List;

public interface IContactsResository {
    public List<ContactsModel> findAll();
}
