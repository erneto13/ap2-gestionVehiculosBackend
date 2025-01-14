package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.ContactModel;
import com.erneto13.sgfa_backend.repository.IContactsResository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService implements IContactsService {

    @Autowired
    private IContactsResository contactRepository;

    @Override
    public List<ContactModel> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public ContactModel getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id " + id));
    }

    @Override
    public ContactModel createContact(ContactModel contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    public ContactModel updateContact(Long id, ContactModel contact) {
        if (contactRepository.existsById(id)) {
            contact.setContact_id(id);
            return contactRepository.save(contact);
        }
        return null;
    }

    public ContactModel updateContactStatus(Long id, String status) {
        ContactModel existingContact = getContactById(id);
        existingContact.setStatus(status);
        return contactRepository.save(existingContact);
    }
}
