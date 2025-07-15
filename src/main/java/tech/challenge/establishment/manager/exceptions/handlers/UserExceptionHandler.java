package tech.challenge.establishment.manager.exceptions.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import tech.challenge.establishment.manager.exceptions.EmailAlreadyExistsException;
import tech.challenge.establishment.manager.exceptions.UserNotExistsException;

@ControllerAdvice
public class UserExceptionHandler {

        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<ValidationErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,
                        WebRequest request) {
                ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                                LocalDateTime.now(),
                                "Email não disponível");
                errorResponse.addFieldError("email", ex.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        
        @ExceptionHandler(UserNotExistsException.class)
        public ResponseEntity<ValidationErrorResponse> handleUserNotExistsException(UserNotExistsException ex,
                        WebRequest request) {
                ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                                LocalDateTime.now(),
                                "Falha na autenticação");
                errorResponse.addFieldError("email", ex.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
}