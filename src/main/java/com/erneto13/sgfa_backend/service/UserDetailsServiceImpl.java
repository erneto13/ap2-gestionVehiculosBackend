package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.UserModel;
import com.erneto13.sgfa_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new User(
                userModel.getEmail(),
                userModel.getPassword(),
                new ArrayList<>()
        );
    }

    public UserModel getUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
