package com.erneto13.sgfa_backend.service;


import com.erneto13.sgfa_backend.model.ContactsModel;
import com.erneto13.sgfa_backend.repository.IContactsResository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService implements IContactsService {

    @Autowired
    private IContactsResository iContactsResository;

    @Override
    public List<ContactsModel> findAll() {
        List<ContactsModel> list;
        try {
            list = iContactsResository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }
}
