package com.martech.analytics.dataprocessing.service;

import com.martech.analytics.dataprocessing.model.enums.Brand;
import com.martech.analytics.dataprocessing.model.request.KPIDataRequest;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface KPIDataService {
    List<KPIDataEntity> getKPIData(@PathVariable("brand-name") Brand brand, @Valid @RequestBody KPIDataRequest dataRequest);
}
