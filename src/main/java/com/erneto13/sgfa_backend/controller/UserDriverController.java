package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.UserDriver;
import com.erneto13.sgfa_backend.service.UserDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user-driver")
public class UserDriverController {

    @Autowired
    private UserDriverService userDriverService;

    @GetMapping
    public ResponseEntity<List<UserDriver>> getAllUserDrivers() {
        List<UserDriver> userDrivers = userDriverService.findAll();
        return new ResponseEntity<>(userDrivers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDriver> getUserDriverById(@PathVariable int id) {
        Optional<UserDriver> userDriver = userDriverService.findById(id);
        return userDriver.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserDriver> createUserDriver(@RequestBody UserDriver userDriver) {
        UserDriver newUserDriver = userDriverService.save(userDriver);
        return new ResponseEntity<>(newUserDriver, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDriver(@PathVariable int id) {
        userDriverService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}