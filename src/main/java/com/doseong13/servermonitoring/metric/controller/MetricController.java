package com.doseong13.servermonitoring.metric.controller;

import com.doseong13.servermonitoring.metric.dto.MetricCreateRequest;
import com.doseong13.servermonitoring.metric.service.MetricService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMetric(
            @RequestHeader("X-Agent-Key") String agentKey,
            @Valid @RequestBody MetricCreateRequest request
    ) {
        metricService.createMetric(agentKey, request);
    }
}