package com.hunghost.onpusocial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such resource")
public class ResourceNotFoundException extends RuntimeException {
    private static final Logger log = LogManager.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
