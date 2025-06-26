package tech.challenge.establishment.manager.dtos;

import tech.challenge.establishment.manager.entities.LoginType;

public record RegisterRequestDto(
        String name,
        String email,
        String password,
        LoginType login,
        Long userId,
        String postalCode,
        String street,
        String neighborhood,
        String city,
        String state) {
}
