package com.example.simplesaga.common.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // TODO check it
@StandardException
public class ValidationApiException extends ApiException {
}
