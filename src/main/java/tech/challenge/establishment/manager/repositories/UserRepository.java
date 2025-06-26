package tech.challenge.establishment.manager.repositories;

import java.util.Optional;

import tech.challenge.establishment.manager.entities.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
