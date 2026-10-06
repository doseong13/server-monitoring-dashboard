package com.doseong13.servermonitoring.metric.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MetricCreateRequest {

    @NotNull
    private LocalDateTime collectedAt;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private BigDecimal cpuUsage;

    @NotNull
    @PositiveOrZero
    private Long memoryUsedMb;

    @NotNull
    @PositiveOrZero
    private Long memoryTotalMb;

    @NotNull
    @PositiveOrZero
    private Long diskUsedMb;

    @NotNull
    @PositiveOrZero
    private Long diskTotalMb;
}