package com.example.simplesaga.user.service.impl;

import com.example.simplesaga.common.exception.ObjectNotFoundApiException;
import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.dto.DepositCreatingRequest;
import com.example.simplesaga.user.dto.WithdrawCreatingRequest;
import com.example.simplesaga.user.entity.Account;
import com.example.simplesaga.user.exception.InvalidWithdrawBalanceApiException;
import com.example.simplesaga.user.mapper.AccountMapper;
import com.example.simplesaga.user.repository.AccountRepository;
import com.example.simplesaga.user.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto deposit(DepositCreatingRequest request, JwtAuthentication jwtAuthentication) {
        final var account = retrieveAccount(request.accountId(), jwtAuthentication);
        account.setAmount(account.getAmount().add(request.amount()));
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(WithdrawCreatingRequest request, JwtAuthentication jwtAuthentication) {
        final var account = retrieveAccount(request.accountId(), jwtAuthentication);
        if (account.getAmount().compareTo(request.amount()) < 0) {
            throw new InvalidWithdrawBalanceApiException();
        }
        account.setAmount(account.getAmount().subtract(request.amount()));
        return accountMapper.toDto(accountRepository.save(account));
    }

    private Account retrieveAccount(UUID accountId, JwtAuthentication jwtAuthentication) {
        return accountRepository.findWithLockByIdAndUserId(
                accountId,
                jwtAuthentication.getId()
        ).orElseThrow(() ->
                new ObjectNotFoundApiException(String.format("Account with id=%s was not found", accountId))
        );
    }
}
