package com.martech.analytics.dataprocessing.batch.mapper;

import com.martech.analytics.dataprocessing.model.KPIDataModel;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;

import java.time.LocalDate;

public class KPIDataMapper {
    public static KPIDataEntity toKPIDataEntity(KPIDataModel kpiDataModel) {
        KPIDataEntity kpiDataEntity = new KPIDataEntity();
        kpiDataEntity.setDate(LocalDate.parse(kpiDataModel.getDate()));
        kpiDataEntity.setOrders(kpiDataModel.getOrders());
        kpiDataEntity.setGrossRevenue(kpiDataModel.getGrossRevenue());
        kpiDataEntity.setGrossMargin(kpiDataModel.getGrossMargin());
        kpiDataEntity.setAddToCarts(kpiDataModel.getAddToCarts());
        kpiDataEntity.setCustomersAcquired(kpiDataModel.getCustomersAcquired());
        kpiDataEntity.setClicks(kpiDataModel.getClicks());
        kpiDataEntity.setMarketingCost(kpiDataModel.getMarketingCost());
        return kpiDataEntity;
    }
}
