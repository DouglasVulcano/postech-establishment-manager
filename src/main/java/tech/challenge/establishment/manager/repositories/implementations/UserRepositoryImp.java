package tech.challenge.establishment.manager.repositories.implementations;

import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.repositories.UserRepository;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jdbcClient.sql("SELECT * FROM users WHERE email = :email")
                .param("email", email).query(User.class)
                .optional();
    }
}
