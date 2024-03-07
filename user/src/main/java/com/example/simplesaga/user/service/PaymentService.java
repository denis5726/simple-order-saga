package com.example.simplesaga.user.service;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.dto.DepositCreatingRequest;
import com.example.simplesaga.user.dto.WithdrawCreatingRequest;

public interface PaymentService {

    AccountDto deposit(DepositCreatingRequest request, JwtAuthentication jwtAuthentication);

    AccountDto withdraw(WithdrawCreatingRequest request, JwtAuthentication jwtAuthentication);
}
