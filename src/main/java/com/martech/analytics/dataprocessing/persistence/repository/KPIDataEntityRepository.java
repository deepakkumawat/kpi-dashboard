package com.martech.analytics.dataprocessing.persistence.repository;

import com.martech.analytics.dataprocessing.model.enums.Brand;
import com.martech.analytics.dataprocessing.persistence.entity.KPIDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KPIDataEntityRepository extends JpaRepository<KPIDataEntity, LocalDate> {
    List<KPIDataEntity> findByBrandAndDateBetween(Brand brand, LocalDate fromDate, LocalDate toDate);
}
