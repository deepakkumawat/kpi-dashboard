package com.martech.analytics.dataprocessing.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorScenario {

    UNKNOWN(HttpStatus.OK, 1001),
    TOKEN_NOT_VALID(HttpStatus.OK, 401),
    ACCOUNT_NOT_FOUND(HttpStatus.OK, 101),
    DUPLICATE_MEMBER(HttpStatus.OK, 102),
    AGGREGATOR_USER_NOT_AVAILABLE(HttpStatus.OK, 103),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 1),
    PRE_CONDITION_FAILED(HttpStatus.PRECONDITION_FAILED, 1),
    SERVICE_INVOCATION_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, 1),
    DOCUMENT_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 1);
    private HttpStatus httpStatus;
    private int code;
}
