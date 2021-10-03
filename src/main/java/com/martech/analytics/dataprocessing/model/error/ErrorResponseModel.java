package com.martech.analytics.dataprocessing.model.error;

import com.martech.analytics.dataprocessing.common.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model representing the error response.")
public class ErrorResponseModel {

    @ApiModelProperty(notes = "Identifier for the base error cause.", required = true)
    private ErrorScenario errorScenario;

    @ApiModelProperty(notes = "Identifier for failure or success.", required = true)
    private int code;

    @ApiModelProperty(notes = "Gives insight of what went wrong to the request we made to an external service.")
    private String vendorStatus;

    @ApiModelProperty(notes = "Describes the error in greater details. It also holds the tracking information.")
    private String message;

    @ApiModelProperty(notes = "Usage: Form validations at server. Suggests why server rejected a particular form path /attribute.")
    private List<FieldError> fieldErrors;

    @ApiModelProperty(notes = "Timestamp when the particular error occurred.")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponseModel(ServiceException ex) {
        this.errorScenario = ex.getScenario();
        this.message = ex.getMessage();
        this.vendorStatus = ex.getVendorStatus();
        this.code = ex.getScenario().getCode();
    }

    public ErrorResponseModel(ErrorScenario errorScenario, String message) {
        this.errorScenario = errorScenario;
        this.message = message;
        this.code = errorScenario.getCode();
    }
}
