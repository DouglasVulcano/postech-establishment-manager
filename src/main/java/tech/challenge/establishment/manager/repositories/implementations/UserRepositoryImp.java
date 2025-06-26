package tech.challenge.establishment.manager.repositories.implementations;

import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import tech.challenge.establishment.manager.entities.LoginType;
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

    @Override
    public User createUser(String name, String email, String password, LoginType login) {
        this.jdbcClient.sql(
                "insert into users (name, email, password, login) values (:name, :email, :password, :login)")
                .param("name", name)
                .param("email", email)
                .param("password", password)
                .param("login", login.name().toLowerCase())
                .update();

        return this.findByEmail(email).orElseThrow(() -> new RuntimeException("Erro ao criar usuário"));
    }
}
