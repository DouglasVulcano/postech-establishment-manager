package tech.challenge.establishment.manager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.repositories.UserRepository;
import tech.challenge.establishment.manager.entities.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User createUser(RegisterRequestDto body) {
        return this.userRepository.createUser(
                body.name(),
                body.email(),
                passwordEncoder.encode(body.password()),
                body.login());
    }
}
