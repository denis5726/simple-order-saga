package com.example.simplesaga.user.exception;

import com.example.simplesaga.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidWithdrawBalanceApiException extends ApiException {

    public InvalidWithdrawBalanceApiException() {
        super("You don't have enough of money for this operation");
    }
}
