package com.example.simplesaga.order.exception;

import com.example.simplesaga.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductCurrencyApiException extends ApiException {

    public InvalidProductCurrencyApiException() {
        super("Invalid product currency");
    }
}
