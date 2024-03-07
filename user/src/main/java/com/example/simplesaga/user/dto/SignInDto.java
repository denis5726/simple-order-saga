package com.example.simplesaga.user.dto;

import com.example.simplesaga.user.validation.BCryptEncoded;
import jakarta.validation.constraints.Email;

public record SignInDto(
        @Email
        String email,
        @BCryptEncoded
        String encodedPassword
) {
}
