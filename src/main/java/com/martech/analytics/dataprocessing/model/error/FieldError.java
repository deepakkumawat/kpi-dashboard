package com.martech.analytics.dataprocessing.model.error;

import java.io.Serializable;

@lombok.Getter
@lombok.AllArgsConstructor
@lombok.ToString
public class FieldError implements Serializable {

    private static final long serialVersionUID = 701069736234843166L;

    private String path;
    private String constraint;
}
