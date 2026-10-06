package com.doseong13.servermonitoring.metric.repository;

import com.doseong13.servermonitoring.metric.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}