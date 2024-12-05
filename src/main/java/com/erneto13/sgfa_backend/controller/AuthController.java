package com.erneto13.sgfa_backend.controller;

import com.erneto13.sgfa_backend.dto.AuthRequestDto;
import com.erneto13.sgfa_backend.dto.AuthResponseDto;
import com.erneto13.sgfa_backend.dto.CreateCredentialsDto;
import com.erneto13.sgfa_backend.model.DriverModel;
import com.erneto13.sgfa_backend.model.UserModel;
import com.erneto13.sgfa_backend.repository.DriverRepository;
import com.erneto13.sgfa_backend.repository.UserRepository;
import com.erneto13.sgfa_backend.service.EmailService;
import com.erneto13.sgfa_backend.service.JwtUtilService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequestDto authRequestDto) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequestDto.getEmail(), authRequestDto.getPassword()));

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequestDto.getEmail());
            UserModel userModel = userRepository.findByEmail(authRequestDto.getEmail());

            String jwt = this.jwtUtilService.generateToken(userDetails, userModel.getRole());
            String refreshToken = this.jwtUtilService.generateRefreshToken(userDetails, userModel.getRole());

            Map<String, Object> response = Map.of(
                    "token", jwt,
                    "refreshToken", refreshToken,
                    "userDetails", userModel
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error Authentication:::" + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> auth(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        try {
            String username = jwtUtilService.extractUsername(refreshToken);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UserModel userModel = userRepository.findByEmail(username);

            if (jwtUtilService.validateToken(refreshToken, userDetails)) {
                String newJwt = jwtUtilService.generateToken(userDetails, userModel.getRole());
                String newRefreshToken = jwtUtilService.generateRefreshToken(userDetails, userModel.getRole());

                AuthResponseDto authResponseDto = new AuthResponseDto();
                authResponseDto.setToken(newJwt);
                authResponseDto.setRefreshToken(newRefreshToken);

                return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error refresh token:::" + e.getMessage());
        }
    }

    @PostMapping("/admin/create-credentials")
    public ResponseEntity<?> createCredentials(@RequestBody CreateCredentialsDto dto) throws MessagingException {
        UserModel user = new UserModel();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("driver");

        DriverModel driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        user.setDriver(driver);

        userRepository.save(user);

        emailService.sendEmail(dto.getEmail(), "Account Created",
                "Your credentials are:\nEmail: " + dto.getEmail() + "\nPassword: " + dto.getPassword());

        return ResponseEntity.ok("Credentials created and sent!");
    }

}
