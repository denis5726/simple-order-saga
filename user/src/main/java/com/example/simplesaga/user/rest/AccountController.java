package com.example.simplesaga.user.rest;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountCreatingRequest;
import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable UUID id, JwtAuthentication jwtAuthentication) {
        return accountService.findById(id, jwtAuthentication);
    }

    @GetMapping
    public List<AccountDto> findAll(JwtAuthentication jwtAuthentication) {
        return accountService.findAll(jwtAuthentication);
    }

    @PostMapping
    public AccountDto create(@RequestBody AccountCreatingRequest request, JwtAuthentication jwtAuthentication) {
        return accountService.create(request, jwtAuthentication);
    }
}
