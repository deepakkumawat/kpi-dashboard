package com.martech.analytics.dataprocessing.common;


import com.martech.analytics.dataprocessing.model.error.ErrorScenario;

/**
 * Exception for all Account service business/known controllable error types.
 *
 * @author deepakk
 */
@lombok.Getter
@lombok.ToString
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7263684210348341559L;

    private final String operation;
    private final ErrorScenario scenario;
    private final String vendorStatus;

    /**
     * Constructs a new service exception with the specified detail message, for specified service, and
     * under said business scenario.
     *
     * @param message      the detail message.
     * @param operation    the operation identifier.
     * @param scenario     the failure cause type.
     * @param vendorStatus the error code from vendor.
     */
    @lombok.Builder
    public ServiceException(String message, String operation, ErrorScenario scenario, String vendorStatus) {
        super(message);
        this.operation = operation;
        this.scenario = scenario;
        this.vendorStatus = vendorStatus;
    }

}
