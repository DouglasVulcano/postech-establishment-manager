package tech.challenge.establishment.manager.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.challenge.establishment.manager.exceptions.EmailAlreadyExistsException;
import tech.challenge.establishment.manager.repositories.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validateEmailNotExists(String email) {
        if (this.userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    public void validateEmailExists(String email) {
        if (this.userRepository.findByEmail(email).isEmpty()) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}