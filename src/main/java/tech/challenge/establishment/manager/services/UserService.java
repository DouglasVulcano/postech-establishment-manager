package tech.challenge.establishment.manager.services;

import java.util.Optional;

import tech.challenge.establishment.manager.dtos.LoginRequestDto;
import tech.challenge.establishment.manager.dtos.RegisterRequestDto;
import tech.challenge.establishment.manager.entities.User;

public interface UserService {

    Optional<User> findByEmail(String email);

    User authenticate(LoginRequestDto body);

    User createUser(RegisterRequestDto body);
}
