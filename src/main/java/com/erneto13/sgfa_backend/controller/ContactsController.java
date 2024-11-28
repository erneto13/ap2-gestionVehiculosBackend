package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.ContactModel;
import com.erneto13.sgfa_backend.model.RemindersModel;
import com.erneto13.sgfa_backend.service.IContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1/contacts")
public class ContactsController {

    @Autowired
    IContactsService iContactsService;

    @GetMapping
    public ResponseEntity<List<ContactModel>> getAllContacts() {
        return new ResponseEntity<>(iContactsService.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactModel> getContactById(@PathVariable Long id) {
        return new ResponseEntity<>(iContactsService.getContactById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactModel> createContact(@RequestBody ContactModel contact) {
        return new ResponseEntity<>(iContactsService.createContact(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        iContactsService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
