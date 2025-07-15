package tech.challenge.establishment.manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.dtos.LoginRequestDto;
import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.dtos.AuthResponseDto;
import tech.challenge.establishment.manager.infra.security.TokenService;
import tech.challenge.establishment.manager.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegisterRequestDto body) {
        User newUser = this.userService.createUser(body);
        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok((new AuthResponseDto(token)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequestDto body) {
        User user = this.userService.authenticate(body);
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok((new AuthResponseDto(token)));
    }
}