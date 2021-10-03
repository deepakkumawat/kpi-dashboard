package com.martech.analytics.dataprocessing.model.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@lombok.Builder
@ApiModel(description = "Model representing accounts response")
public class KPIDataResponse implements Serializable {
    private static final long serialVersionUID = 701069736234843166L;

}
