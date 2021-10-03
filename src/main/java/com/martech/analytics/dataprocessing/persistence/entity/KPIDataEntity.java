package com.martech.analytics.dataprocessing.persistence.entity;

import com.martech.analytics.dataprocessing.model.enums.Brand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@lombok.Getter
@lombok.Setter
@Table(name = "BRAND_KPI_RECORDS")
public class KPIDataEntity {

    @Id
    private LocalDate date;

    @Column
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Column
    private Long orders;
    @Column
    private Long grossRevenue;
    @Column
    private Long grossMargin;
    @Column
    private Long addToCarts;
    @Column
    private Long customersAcquired;
    @Column
    private Long clicks;
    @Column
    private Long marketingCost;
    @Column
    private Long aov;
    @Column
    private Long cvr;
    @Column
    private Long car;
    @Column
    private Long cac;
    @Column
    private Long averageProfitMargin;
    @Column
    private Long rpc;

}
