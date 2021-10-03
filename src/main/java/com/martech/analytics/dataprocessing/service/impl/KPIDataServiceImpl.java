package com.martech.analytics.dataprocessing.service.impl;

import com.martech.analytics.dataprocessing.model.enums.Brand;
import com.martech.analytics.dataprocessing.model.request.KPIDataRequest;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import com.martech.analytics.dataprocessing.persistence.repository.KPIDataEntityRepository;
import com.martech.analytics.dataprocessing.service.KPIDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KPIDataServiceImpl implements KPIDataService {

    @Autowired
    private KPIDataEntityRepository kpiDataEntityRepository;

    @Override
    public List<KPIDataEntity> getKPIData(Brand brand, KPIDataRequest dataRequest) {
        return kpiDataEntityRepository.findByBrandAndDateBetween(brand, dataRequest.getFromDate(), dataRequest.getToDate());
    }
}
