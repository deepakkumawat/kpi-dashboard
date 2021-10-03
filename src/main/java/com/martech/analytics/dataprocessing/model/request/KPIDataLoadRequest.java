package com.martech.analytics.dataprocessing.model.request;

import com.martech.analytics.dataprocessing.model.enums.Brand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@ApiModel(description = "KPI data load model")
public class KPIDataLoadRequest implements Serializable {

    private static final long serialVersionUID = 701069736234843166L;

    @ApiModelProperty("For which brand, we are uploading the data")
    private Brand brand;

    @ApiModelProperty("Do you wants to merge or replace the data")
    private boolean merge;

}
