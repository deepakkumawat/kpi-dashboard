package com.martech.analytics.dataprocessing.model.error;


import java.util.Collections;
import java.util.List;

public class FormValidationException extends RuntimeException {

    private static final long serialVersionUID = -7451846567530481450L;

    @lombok.Getter
    private final transient List<FieldError> errors;

    public FormValidationException(String message, List<FieldError> errors) {
        super(message);
        this.errors = Collections.unmodifiableList(errors);
    }
}
