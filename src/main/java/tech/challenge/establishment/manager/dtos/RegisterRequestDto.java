package tech.challenge.establishment.manager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import tech.challenge.establishment.manager.entities.UserRole;

public record RegisterRequestDto(

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres")
    String password,

    @NotNull(message = "A role é obrigatória")
    UserRole role,

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve estar no formato 00000-000 ou 00000000")
    String postalCode,

    @NotBlank(message = "A rua é obrigatória")
    @Size(max = 255, message = "A rua deve ter no máximo 255 caracteres")
    String street,

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    String city,

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 letras (UF)")
    @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve conter apenas letras maiúsculas (ex: SP, RJ)")
    String state
) {}
