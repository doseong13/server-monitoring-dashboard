package com.doseong13.servermonitoring.metric.domain;

import com.doseong13.servermonitoring.server.domain.Server;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @Column(name = "collected_at", nullable = false)
    private LocalDateTime collectedAt;

    @Column(name = "cpu_usage", nullable = false, precision = 5, scale = 2)
    private BigDecimal cpuUsage;

    @Column(name = "memory_used_mb", nullable = false)
    private Long memoryUsedMb;

    @Column(name = "memory_total_mb", nullable = false)
    private Long memoryTotalMb;

    @Column(name = "disk_used_mb", nullable = false)
    private Long diskUsedMb;

    @Column(name = "disk_total_mb", nullable = false)
    private Long diskTotalMb;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Metric(
            Server server,
            LocalDateTime collectedAt,
            BigDecimal cpuUsage,
            Long memoryUsedMb,
            Long memoryTotalMb,
            Long diskUsedMb,
            Long diskTotalMb
    ) {
        this.server = server;
        this.collectedAt = collectedAt;
        this.cpuUsage = cpuUsage;
        this.memoryUsedMb = memoryUsedMb;
        this.memoryTotalMb = memoryTotalMb;
        this.diskUsedMb = diskUsedMb;
        this.diskTotalMb = diskTotalMb;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}