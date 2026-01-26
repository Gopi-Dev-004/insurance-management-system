package com.insurance.insurancemanagementsystem.auth;

import com.insurance.insurancemanagementsystem.config.security.JwtUtil;
import com.insurance.insurancemanagementsystem.customer.controller.CustomersRegistrationDTO;
import com.insurance.insurancemanagementsystem.user.Repository.RoleRepository;
import com.insurance.insurancemanagementsystem.user.Repository.UserRepository;
import com.insurance.insurancemanagementsystem.user.entity.Role;
import com.insurance.insurancemanagementsystem.user.entity.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class Controller {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    private AuthenticationManager authManager;
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> UserLogin(@RequestBody LoginDto dto) {

        try {
            System.out.println("line...............................................1");
            Authentication authentication =
                    authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));

            System.out.println("line...............................................2");

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            System.out.println("line...............................................3");

            String token = jwtUtil.generateToken(userDetails);
            System.out.println("line...............................................4");

            return ResponseEntity.ok(Map.of("token", token));



        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("line...............................................5");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "invalid username and password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@Valid @RequestBody CustomersRegistrationDTO dto) {

        User user = new User();
        if (userRepository.findByUsername(dto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        user.setUsername(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAccountStatus("ACTIVE");
        Role role = roleRepository.findByRoleName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);

        return ResponseEntity.ok("Customer registration successfully");
    }
}


