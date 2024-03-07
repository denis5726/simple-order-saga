package com.example.simplesaga.user.service;

import com.example.simplesaga.user.dto.SignInDto;
import com.example.simplesaga.user.dto.SignUpDto;
import com.example.simplesaga.user.dto.TokenDto;
import com.example.simplesaga.user.dto.UserDto;

public interface AuthService {

    TokenDto signIn(SignInDto signInDto);

    UserDto signUp(SignUpDto signUpDto);
}
