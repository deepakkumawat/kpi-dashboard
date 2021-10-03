package com.martech.analytics.dataprocessing.controller;

import com.martech.analytics.dataprocessing.model.enums.Brand;
import com.martech.analytics.dataprocessing.model.request.KPIDataLoadRequest;
import com.martech.analytics.dataprocessing.model.request.KPIDataRequest;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import com.martech.analytics.dataprocessing.service.KPIDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/kpi/data")
@Api(tags = {"KPIController"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class KPIDataController {

    @Autowired
    private KPIDataService kpiDataService;

    @PostMapping("/{brand-name}")
    @ApiOperation("Return KPI data")
    public List<KPIDataEntity> getKPIData(@PathVariable("brand-name") Brand brand, @Valid @RequestBody KPIDataRequest dataRequest) {
        return kpiDataService.getKPIData(brand, dataRequest);
    }

    @PostMapping("/{brand-name}/load")
    @ApiOperation(value = "Load the KPI data", produces = APPLICATION_JSON_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
    public void loadData(@PathVariable("brand-name") Brand brand, @Valid @RequestBody KPIDataLoadRequest data) {
        return;
    }

}
