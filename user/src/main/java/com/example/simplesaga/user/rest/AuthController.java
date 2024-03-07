package com.example.simplesaga.user.rest;

import com.example.simplesaga.user.dto.SignInDto;
import com.example.simplesaga.user.dto.SignUpDto;
import com.example.simplesaga.user.dto.TokenDto;
import com.example.simplesaga.user.dto.UserDto;
import com.example.simplesaga.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signIn")
    public TokenDto signIn(@RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }

    @PostMapping("/signUp")
    public UserDto signUp(@RequestBody SignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }
}
