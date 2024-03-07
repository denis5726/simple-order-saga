package com.example.simplesaga.user.dto;

import com.example.simplesaga.user.validation.BCryptEncoded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record SignUpDto(
        @Email
        @Length(max = 255)
        String email,
        @NotBlank
        @Length(max = 255)
        String firstName,
        @NotBlank
        @Length(max = 255)
        String lastName,
        @BCryptEncoded
        String passwordHash,
        @Past
        LocalDate birth
) {
}
