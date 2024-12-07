package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.UserDriver;
import com.erneto13.sgfa_backend.repository.UserDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDriverService {

    @Autowired
    private UserDriverRepository userDriverRepository;

    public List<UserDriver> findAll() {
        return userDriverRepository.findAll();
    }

    public Optional<UserDriver> findById(int id) {
        return userDriverRepository.findById(id);
    }

    public UserDriver save(UserDriver userDriver) {
        return userDriverRepository.save(userDriver);
    }

    public void deleteById(int id) {
        userDriverRepository.deleteById(id);
    }
}