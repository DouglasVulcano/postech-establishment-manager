package tech.challenge.establishment.manager.services.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.entities.Address;
import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.repositories.AddressRepository;
import tech.challenge.establishment.manager.repositories.UserRepository;
import tech.challenge.establishment.manager.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;              

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(RegisterRequestDto body) {
        try {
            User user = new User(body.name(), body.email(), body.login(), this.passwordEncoder.encode(body.password()));
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
}
