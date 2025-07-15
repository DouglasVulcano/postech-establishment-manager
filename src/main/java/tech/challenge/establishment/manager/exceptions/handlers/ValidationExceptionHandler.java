package tech.challenge.establishment.manager.exceptions.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ValidationExceptionHandler {
        
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
                MethodArgumentNotValidException ex, WebRequest request) {
                
                ValidationErrorResponse errorResponse = new ValidationErrorResponse(
                        LocalDateTime.now(), 
                        "Erro de validação");
                        
                ex.getBindingResult().getAllErrors().forEach((error) -> {
                        String fieldName = error instanceof FieldError ? 
                                ((FieldError) error).getField() : error.getObjectName();
                        String errorMessage = error.getDefaultMessage();
                        errorResponse.addFieldError(fieldName, errorMessage);
                });
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

}