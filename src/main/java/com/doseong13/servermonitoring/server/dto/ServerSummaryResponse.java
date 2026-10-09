package com.doseong13.servermonitoring.server.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ServerSummaryResponse {

    private final Long id;
    private final String name;
    private final BigDecimal cpuThreshold;
    private final LocalDateTime lastSeenAt;

    public ServerSummaryResponse(
            Long id,
            String name,
            BigDecimal cpuThreshold,
            LocalDateTime lastSeenAt
    ) {
        this.id = id;
        this.name = name;
        this.cpuThreshold = cpuThreshold;
        this.lastSeenAt = lastSeenAt;
    }
}