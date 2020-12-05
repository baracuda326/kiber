package com.gateway.gateway.exceptions;

public class RegistrationModelException extends RuntimeException {
    public RegistrationModelException(String message) {
        super(message);
    }

    public RegistrationModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
