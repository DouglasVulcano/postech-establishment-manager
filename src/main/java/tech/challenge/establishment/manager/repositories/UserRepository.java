package tech.challenge.establishment.manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.challenge.establishment.manager.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}