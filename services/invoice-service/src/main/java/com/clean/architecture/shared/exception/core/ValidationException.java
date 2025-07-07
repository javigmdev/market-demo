package com.clean.architecture.shared.exception.core;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    private final Map<String, String> fieldErrors;

    public ValidationException(String message) {
        super(message);
        this.fieldErrors = new HashMap<>();
    }

    public ValidationException(Map<String, String> fieldErrors) {
        super("Validation failed");
        this.fieldErrors = new HashMap<>(fieldErrors);
    }

    public ValidationException(String field, String message) {
        super(message);
        this.fieldErrors = Map.of(field, message);
    }
}
