package com.doseong13.servermonitoring.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "agent_key_hash", nullable = false, unique = true, length = 255)
    private String agentKeyHash;

    /**
     * BigDecimal: 소수점 정확도가 필요한 값에 사용
     * precision: 전체 숫자 자릿수는 최대 5자리, scale: 소수점 아래 자릿수는 2자리
     */

    @Column(name = "cpu_threshold", nullable = false, precision = 5, scale = 2)
    private BigDecimal cpuThreshold;

    @Column(name = "last_seen_at")
    private LocalDateTime lastSeenAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Server(String name, String agentKeyHash, BigDecimal cpuThreshold) {
        this.name = name;
        this.agentKeyHash = agentKeyHash;
        this.cpuThreshold = cpuThreshold;
    }

    public void updateLastSeenAt(LocalDateTime lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }

    @PrePersist
    private void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}