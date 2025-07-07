package com.clean.architecture.shared.exception.core;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ValidationResult {

    private final Map<String, String> errors = new HashMap<>();

    public void addError(String field, String messageKey) {
        errors.put(field, messageKey);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void throwIfInvalid() {
        if (hasErrors()) {
            throw new ValidationException(errors);
        }
    }
}
