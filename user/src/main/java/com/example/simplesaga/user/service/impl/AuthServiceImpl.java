package com.example.simplesaga.user.service.impl;

import com.example.simplesaga.common.exception.ObjectNotFoundApiException;
import com.example.simplesaga.user.dto.SignInDto;
import com.example.simplesaga.user.dto.SignUpDto;
import com.example.simplesaga.user.dto.TokenDto;
import com.example.simplesaga.user.dto.UserDto;
import com.example.simplesaga.user.exception.InvalidPasswordException;
import com.example.simplesaga.user.exception.RegistrationWithExistentEmailApiException;
import com.example.simplesaga.user.mapper.UserMapper;
import com.example.simplesaga.user.repository.UserRepository;
import com.example.simplesaga.user.service.AuthService;
import com.example.simplesaga.user.service.TokenBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenBuilder tokenBuilder;
    private final UserMapper userMapper;

    @Override
    public TokenDto signIn(SignInDto signInDto) {
        log.info("Handling signIn request: {}", signInDto);
        final var user = userRepository.findByEmail(signInDto.email())
                .orElseThrow(() -> new ObjectNotFoundApiException(String.format(
                        "User with email=%s was not found", signInDto.email()
                )));
        if (!passwordEncoder.matches(CharBuffer.wrap(signInDto.encodedPassword()), user.getPasswordHash())) {
            log.error("Password verification failed for: {}", signInDto);
            throw new InvalidPasswordException();
        }
        return new TokenDto(tokenBuilder.buildToken(user));
    }

    @Override
    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        log.info("Handling signUp request: {}", signUpDto);
        final var userEntity = userMapper.fromSignUpDto(signUpDto);
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RegistrationWithExistentEmailApiException();
        }
        return userMapper.toDto(userRepository.save(userEntity));
    }
}
