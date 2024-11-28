package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.ContactModel;

import java.util.List;

public interface IContactsService {
    List<ContactModel> getAllContacts();
    ContactModel getContactById(Long id);
    ContactModel createContact(ContactModel contact);
    void deleteContact(Long id);
}
