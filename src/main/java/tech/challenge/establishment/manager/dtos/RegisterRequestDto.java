package tech.challenge.establishment.manager.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import tech.challenge.establishment.manager.config.EnumCaseInsensitiveConverter;
import tech.challenge.establishment.manager.entities.LoginType;

public record RegisterRequestDto(
        String name,
        String email,
        String password,
        LoginType login,
        String postalCode,
        String street,
        String city,
        String state) {
}
