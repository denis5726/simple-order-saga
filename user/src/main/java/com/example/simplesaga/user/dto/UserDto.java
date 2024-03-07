package com.example.simplesaga.user.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        LocalDate birth
) {
}
