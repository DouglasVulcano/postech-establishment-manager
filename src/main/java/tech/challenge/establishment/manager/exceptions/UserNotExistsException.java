package tech.challenge.establishment.manager.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String email) {
        super("Email ou senha inválidos");
    }
}