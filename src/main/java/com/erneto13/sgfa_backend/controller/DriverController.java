package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("api/v1")
public class DriverController {

    @GetMapping("driver/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsServiceImpl.CustomUserDetails) {
            UserDetailsServiceImpl.CustomUserDetails userDetails = (UserDetailsServiceImpl.CustomUserDetails) authentication.getPrincipal();
            DriverModel driverInfo = userDetails.getDriverInfo();
            return ResponseEntity.ok(driverInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
