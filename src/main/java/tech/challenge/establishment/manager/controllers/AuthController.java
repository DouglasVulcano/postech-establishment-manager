package tech.challenge.establishment.manager.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
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
}