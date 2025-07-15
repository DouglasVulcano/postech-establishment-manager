package tech.challenge.establishment.manager.services.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.challenge.establishment.manager.dtos.LoginRequestDto;
import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.entities.Address;
import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.repositories.AddressRepository;
import tech.challenge.establishment.manager.repositories.UserRepository;
import tech.challenge.establishment.manager.services.UserService;
import tech.challenge.establishment.manager.validators.UserValidator;
import tech.challenge.establishment.manager.exceptions.UserNotExistsException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final UserValidator userValidator;

    @Override
    public Optional<User> findByEmail(String email) {
        this.userValidator.validateEmailExists(email);
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(RegisterRequestDto body) {
        this.userValidator.validateEmailNotExists(body.email());

        try {
            User user = new User(body.name(), body.email(), this.passwordEncoder.encode(body.password()));
            user.setRole(body.role()); // Define explicitamente a role do usuário
            User newUser = this.userRepository.save(user);

            Address address = new Address(body.postalCode(), body.street(), body.city(),
                    body.state(), newUser);

            Address savedAddress = this.addressRepository.save(address);
            newUser.setAddress(savedAddress);
            return newUser;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public User authenticate(LoginRequestDto body) {
        User user = this.findByEmail(body.email()).orElseThrow(() -> new UserNotExistsException(body.email()));
        if (this.passwordEncoder.matches(body.password(), user.getPassword())) {
            return user;
        }
        throw new UserNotExistsException(body.email()); 
    }
}
