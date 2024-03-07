package com.example.simplesaga.user.exception;

import com.example.simplesaga.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends ApiException {

    public InvalidPasswordException() {
        super("Provided invalid password");
    }
}
