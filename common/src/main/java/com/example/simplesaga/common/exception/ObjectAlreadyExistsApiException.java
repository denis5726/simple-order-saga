package com.example.simplesaga.common.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@StandardException
public class ObjectAlreadyExistsApiException extends ApiException {
}
