package com.example.simplesaga.user.rest;

import com.example.simplesaga.common.security.model.JwtAuthentication;
import com.example.simplesaga.user.dto.AccountDto;
import com.example.simplesaga.user.dto.DepositCreatingRequest;
import com.example.simplesaga.user.dto.WithdrawCreatingRequest;
import com.example.simplesaga.user.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/deposit")
    private AccountDto deposit(@RequestBody DepositCreatingRequest request, JwtAuthentication jwtAuthentication) {
        return paymentService.deposit(request, jwtAuthentication);
    }

    @PostMapping("/withdraw")
    private AccountDto withdraw(@RequestBody WithdrawCreatingRequest request, JwtAuthentication jwtAuthentication) {
        return paymentService.withdraw(request, jwtAuthentication);
    }
}
