package com.example.simplesaga.user.exception;

import com.example.simplesaga.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistrationWithExistentEmailApiException extends ApiException {

    public RegistrationWithExistentEmailApiException() {
        super("User with this email already exists");
    }
}
