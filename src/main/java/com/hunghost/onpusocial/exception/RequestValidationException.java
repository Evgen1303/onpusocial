package com.hunghost.onpusocial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestValidationException extends ValidationException {

    public RequestValidationException() {
    }

    public RequestValidationException(String message) {
        super(message);
    }
}
