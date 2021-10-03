package com.martech.analytics.dataprocessing.batch;

import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import com.martech.analytics.dataprocessing.persistence.repository.KPIDataEntityRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<KPIDataEntity> {

    @Autowired
    private KPIDataEntityRepository kpiDataEntityRepository;

    @Override
    public void write(List<? extends KPIDataEntity> items) throws Exception {
        kpiDataEntityRepository.saveAll(items);
    }
}
