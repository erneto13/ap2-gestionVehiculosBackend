package com.erneto13.sgfa_backend.service;

import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.model.UserModel;
import com.erneto13.sgfa_backend.repository.IDriverRepository;
import com.erneto13.sgfa_backend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IDriverRepository iDriverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Call Database to validate
        UserModel userModel = this.iUserRepository.findByEmail(email);
        if (userModel == null) {
            throw new UsernameNotFoundException(email);
        }

        // Get driver information
        DriverModel driverInfo = this.iDriverRepository.findByUserId(userModel.getUser_id());

        // Create authorities list
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userModel.getRole()));

        return new CustomUserDetails(
                userModel.getEmail(),
                userModel.getPassword(),
                authorities,
                driverInfo
        );
    }

    public class CustomUserDetails extends User {
        private final DriverModel driverInfo;

        public CustomUserDetails(String username, String password,
                                 Collection<? extends GrantedAuthority> authorities,
                                 DriverModel driverInfo) {
            super(username, password, authorities);
            this.driverInfo = driverInfo;
        }

        public DriverModel getDriverInfo() {
            return driverInfo;
        }
    }
}
