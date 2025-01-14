package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.ContactModel;
import com.erneto13.sgfa_backend.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping
    public List<ContactModel> getAllContacts() {
        return contactsService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactModel> getContactById(@PathVariable Long id) {
        ContactModel contact = contactsService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<ContactModel> createContact(@RequestBody ContactModel contact) {
        ContactModel createdContact = contactsService.createContact(contact);
        return ResponseEntity.ok(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactModel> updateContact(@PathVariable Long id, @RequestBody ContactModel updatedContact) {
        ContactModel updated = contactsService.updateContact(id, updatedContact);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ContactModel> updateContactStatus(@PathVariable Long id, @RequestParam String status) {
        ContactModel updatedContact = contactsService.updateContactStatus(id, status);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactsService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
