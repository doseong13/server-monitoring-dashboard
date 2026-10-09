package com.doseong13.servermonitoring.metric.repository;

import com.doseong13.servermonitoring.metric.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    Optional<Metric> findTopByServerIdOrderByCollectedAtDesc(Long serverId);
}