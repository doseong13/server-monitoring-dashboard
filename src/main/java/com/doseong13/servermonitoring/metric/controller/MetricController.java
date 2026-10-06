package com.doseong13.servermonitoring.metric.controller;

import com.doseong13.servermonitoring.metric.dto.MetricCreateRequest;
import com.doseong13.servermonitoring.metric.service.MetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Metrics",
        description = "VM 서버 상태 지표 수집 API"
)
@RestController
@RequestMapping("/api/v1/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @Operation(
            summary = "서버 지표 수집",
            description = "에이전트가 CPU, 메모리, 디스크 사용량을 전송합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "지표 저장 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 지표 값"),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 에이전트 키")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMetric(
            @Parameter(
                    name = "X-Agent-Key",
                    description = "서버별 에이전트 인증 키",
                    required = true,
                    in = ParameterIn.HEADER,
                    example = "work1-local-dev-key"
            )
            @RequestHeader("X-Agent-Key") String agentKey,
            @Valid @RequestBody MetricCreateRequest request
    ) {
        metricService.createMetric(agentKey, request);
    }
}