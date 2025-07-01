package tech.challenge.establishment.manager.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.challenge.establishment.manager.dtos.LoginRequestDto;
import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.infra.security.TokenService;
import tech.challenge.establishment.manager.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto body) {
        Optional<User> user = userService.findByEmail(body.email());
        if (user.isEmpty()) {
            User newUser = userService.createUser(body);
            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto body) {
        User user = userService.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User Not Found"));
        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(token);
    }
}