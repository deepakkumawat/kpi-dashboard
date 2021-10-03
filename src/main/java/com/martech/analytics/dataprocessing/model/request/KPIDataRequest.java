package com.martech.analytics.dataprocessing.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

@lombok.Getter
@lombok.Setter
@ApiModel(description = "KPI data request model")
public class KPIDataRequest implements Serializable {

    private static final long serialVersionUID = 701069736234843166L;

    @ApiModelProperty("From date")
    private LocalDate fromDate;

    @ApiModelProperty("To date")
    private LocalDate toDate;

}
