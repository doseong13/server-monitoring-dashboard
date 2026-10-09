package com.doseong13.servermonitoring.metric.dto;

import com.doseong13.servermonitoring.metric.domain.Metric;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class LatestMetricResponse {

    private final Long serverId;
    private final LocalDateTime collectedAt;
    private final BigDecimal cpuUsage;
    private final Long memoryUsedMb;
    private final Long memoryTotalMb;
    private final Long diskUsedMb;
    private final Long diskTotalMb;

    private LatestMetricResponse(
            Long serverId,
            LocalDateTime collectedAt,
            BigDecimal cpuUsage,
            Long memoryUsedMb,
            Long memoryTotalMb,
            Long diskUsedMb,
            Long diskTotalMb
    ) {
        this.serverId = serverId;
        this.collectedAt = collectedAt;
        this.cpuUsage = cpuUsage;
        this.memoryUsedMb = memoryUsedMb;
        this.memoryTotalMb = memoryTotalMb;
        this.diskUsedMb = diskUsedMb;
        this.diskTotalMb = diskTotalMb;
    }

    public static LatestMetricResponse from(Metric metric) {
        return new LatestMetricResponse(
                metric.getServer().getId(),
                metric.getCollectedAt(),
                metric.getCpuUsage(),
                metric.getMemoryUsedMb(),
                metric.getMemoryTotalMb(),
                metric.getDiskUsedMb(),
                metric.getDiskTotalMb()
        );
    }
}