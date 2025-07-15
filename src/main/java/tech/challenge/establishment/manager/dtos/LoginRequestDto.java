package tech.challenge.establishment.manager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotNull(message = "A senha é obrigatória")
        String password) {
}
