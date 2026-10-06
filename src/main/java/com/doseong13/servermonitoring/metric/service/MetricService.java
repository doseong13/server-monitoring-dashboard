package com.doseong13.servermonitoring.metric.service;

import com.doseong13.servermonitoring.metric.domain.Metric;
import com.doseong13.servermonitoring.metric.dto.MetricCreateRequest;
import com.doseong13.servermonitoring.metric.repository.MetricRepository;
import com.doseong13.servermonitoring.server.domain.Server;
import com.doseong13.servermonitoring.server.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HexFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class MetricService {

    private final ServerRepository serverRepository;
    private final MetricRepository metricRepository;

    public void createMetric(String agentKey, MetricCreateRequest request) {
        validateUsage(request);

        String agentKeyHash = hashAgentKey(agentKey);

        Server server = serverRepository.findByAgentKeyHash(agentKeyHash)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "유효하지 않은 에이전트 키입니다."
                ));

        Metric metric = new Metric(
                server,
                request.getCollectedAt(),
                request.getCpuUsage(),
                request.getMemoryUsedMb(),
                request.getMemoryTotalMb(),
                request.getDiskUsedMb(),
                request.getDiskTotalMb()
        );

        metricRepository.save(metric);

        server.updateLastSeenAt(LocalDateTime.now());
    }

    private void validateUsage(MetricCreateRequest request) {
        if (request.getMemoryUsedMb() > request.getMemoryTotalMb()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "메모리 사용량은 전체 메모리보다 클 수 없습니다."
            );
        }

        if (request.getDiskUsedMb() > request.getDiskTotalMb()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "디스크 사용량은 전체 디스크보다 클 수 없습니다."
            );
        }
    }

    private String hashAgentKey(String agentKey) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            byte[] hashedBytes = messageDigest.digest(
                    agentKey.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 알고리즘을 사용할 수 없습니다.", e);
        }
    }
}