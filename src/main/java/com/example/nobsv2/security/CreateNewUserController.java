package com.example.nobsv2.security;

import com.example.nobsv2.mappings.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewUserController {
    private final PasswordEncoder encoder;
    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder encoder, CustomerRepository customerRepository, CustomUserRepository customUserRepository) {
        this.encoder = encoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());
        if(optionalUser.isEmpty()) {
            customUserRepository.save(new CustomUser(user.getUsername(), encoder.encode(user.getPassword())));
            return ResponseEntity.ok("New user created");
        }
        return ResponseEntity.badRequest().body("Failed to create new user");
    }
}
