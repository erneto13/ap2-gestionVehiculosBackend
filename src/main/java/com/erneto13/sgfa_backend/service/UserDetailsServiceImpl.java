package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.UserModel;
import com.erneto13.sgfa_backend.repository.IUserRepository;
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
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Call Database to validate
        UserModel userModel = this.iUserRepository.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(
                userModel.getEmail(),
                userModel.getPassword(),
                new ArrayList<>());
    }
}
