package tech.challenge.establishment.manager.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email não disponível");
    }
}