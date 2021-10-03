package com.martech.analytics.dataprocessing.batch;

import com.martech.analytics.dataprocessing.batch.mapper.KPIDataMapper;
import com.martech.analytics.dataprocessing.model.KPIDataModel;
import com.martech.analytics.dataprocessing.model.enums.Brand;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class Processor implements ItemProcessor<KPIDataModel, KPIDataEntity> {

    @Override
    public KPIDataEntity process(KPIDataModel item) throws Exception {
        KPIDataEntity kpiDataEntity = KPIDataMapper.toKPIDataEntity(item);
        kpiDataEntity.setBrand(Brand.ABC);
        calculateKPIs(kpiDataEntity);
        return kpiDataEntity;
    }

    private void calculateKPIs(KPIDataEntity kpiDataEntity) {
        kpiDataEntity.setAov(kpiDataEntity.getGrossRevenue() / kpiDataEntity.getOrders());
        kpiDataEntity.setCvr((kpiDataEntity.getOrders()*100)/kpiDataEntity.getClicks());
        kpiDataEntity.setCar(0l);
        kpiDataEntity.setCac(kpiDataEntity.getMarketingCost()/kpiDataEntity.getOrders());
        kpiDataEntity.setAverageProfitMargin(0l);
        kpiDataEntity.setRpc(kpiDataEntity.getGrossRevenue() / kpiDataEntity.getCustomersAcquired());
    }
}
