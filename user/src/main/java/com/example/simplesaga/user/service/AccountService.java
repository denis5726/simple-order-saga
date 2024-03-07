package com.example.simplesaga.user.service;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountCreatingRequest;
import com.example.simplesaga.user.dto.AccountDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountDto findById(UUID id, JwtAuthentication jwtAuthentication);

    List<AccountDto> findAll(JwtAuthentication jwtAuthentication);

    AccountDto create(AccountCreatingRequest request, JwtAuthentication jwtAuthentication);
}
