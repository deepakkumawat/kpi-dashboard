package com.martech.analytics.dataprocessing.model;

@lombok.Getter
@lombok.Setter
public class KPIDataModel {

    private String date;

    private Long orders;

    private Long grossRevenue;

    private Long grossMargin;

    private Long addToCarts;

    private Long customersAcquired;

    private Long clicks;

    private Long marketingCost;

}
