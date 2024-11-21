package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.ContactModel;
import com.erneto13.sgfa_backend.service.IContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ContactsController {

    @Autowired
    IContactsService iContactsService;

    @GetMapping("/contact-list")
    public ResponseEntity<?> list() {
        List<ContactModel> contacts = this.iContactsService.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

}
