package com.example.simplesaga.user.service.impl;

import com.example.simplesaga.common.exception.ObjectAlreadyExistsApiException;
import com.example.simplesaga.common.exception.ObjectNotFoundApiException;
import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountCreatingRequest;
import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.entity.Account;
import com.example.simplesaga.user.exception.InvalidAccountCurrencyApiException;
import com.example.simplesaga.user.mapper.AccountMapper;
import com.example.simplesaga.user.repository.AccountRepository;
import com.example.simplesaga.user.repository.UserRepository;
import com.example.simplesaga.user.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private static final Set<Currency> VALID_CURRENCIES = Set.of(
            Currency.getInstance("RUB"),
            Currency.getInstance("USD")
    );
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;

    @Override
    public AccountDto findById(UUID id, JwtAuthentication jwtAuthentication) {
        log.info("Getting account by id={}, authentication={}", id, jwtAuthentication);
        return accountMapper.toDto(
                accountRepository.findByIdAndUserId(id, jwtAuthentication.getId())
                        .orElseThrow(() -> new ObjectNotFoundApiException(
                                String.format("Account with id=%s was not found", id)
                        ))
        );
    }

    @Override
    public List<AccountDto> findAll(JwtAuthentication jwtAuthentication) {
        log.info("Getting accounts by authentication={}", jwtAuthentication);
        return accountMapper.toDtoList(accountRepository.findByUserId(jwtAuthentication.getId()));
    }

    @Override
    public AccountDto create(AccountCreatingRequest request, JwtAuthentication jwtAuthentication) {
        log.info("Creating account from request={}, authentication={}", request, jwtAuthentication);
        if (!VALID_CURRENCIES.contains(request.currency())) {
            log.error("Provided invalid currency: {}", request.currency());
            throw new InvalidAccountCurrencyApiException();
        }
        if (accountRepository.existsByUserIdAndCurrency(
                jwtAuthentication.getId(),
                request.currency().getCurrencyCode())
        ) {
            log.error(
                    "Account with userId={} and currency={} already exists",
                    jwtAuthentication.getId(),
                    request.currency()
            );
            throw new ObjectAlreadyExistsApiException(String.format(
                    "Account with currency=%s already exists", request.currency()
            ));
        }
        return accountMapper.toDto(
                accountRepository.save(
                        Account.builder()
                                .user(userRepository.findById(jwtAuthentication.getId()).orElseThrow())
                                .amount(BigDecimal.ZERO)
                                .currency(request.currency())
                                .build()
                )
        );
    }
}
