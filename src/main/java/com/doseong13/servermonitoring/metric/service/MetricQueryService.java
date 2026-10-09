package com.doseong13.servermonitoring.metric.service;

import com.doseong13.servermonitoring.metric.domain.Metric;
import com.doseong13.servermonitoring.metric.dto.LatestMetricResponse;
import com.doseong13.servermonitoring.metric.repository.MetricRepository;
import com.doseong13.servermonitoring.server.repository.ServerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
public class MetricQueryService {

    private final ServerRepository serverRepository;
    private final MetricRepository metricRepository;

    public MetricQueryService(
            ServerRepository serverRepository,
            MetricRepository metricRepository
    ) {
        this.serverRepository = serverRepository;
        this.metricRepository = metricRepository;
    }

    public LatestMetricResponse getLatestMetric(Long serverId) {
        serverRepository.findById(serverId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "존재하지 않는 서버입니다."
                ));

        Metric metric = metricRepository.findTopByServerIdOrderByCollectedAtDesc(serverId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "해당 서버의 수집된 지표가 없습니다."
                ));

        return LatestMetricResponse.from(metric);
    }
}