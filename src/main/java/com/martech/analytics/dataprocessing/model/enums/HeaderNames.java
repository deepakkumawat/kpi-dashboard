package com.martech.analytics.dataprocessing.model.enums;

public enum HeaderNames {

    USER_ID("x-user-id"),
    REQUEST_ID("x-request-id"),
    LANGUAGE("accept-language"),
    TOKEN("Authorization"),
    AUTH_CONTEXT("auth-context");
    private String value;

    HeaderNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
