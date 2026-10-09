package com.doseong13.servermonitoring.server.controller;

import com.doseong13.servermonitoring.server.dto.ServerSummaryResponse;
import com.doseong13.servermonitoring.server.service.ServerQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doseong13.servermonitoring.metric.dto.LatestMetricResponse;
import com.doseong13.servermonitoring.metric.service.MetricQueryService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(
        name = "Servers",
        description = "모니터링 대상 서버 조회 API"
)
@RestController
@RequestMapping("/api/v1/servers")
public class ServerController {

    private final ServerQueryService serverQueryService;
    private final MetricQueryService metricQueryService;

    public ServerController(
            ServerQueryService serverQueryService,
            MetricQueryService metricQueryService
    ) {
        this.serverQueryService = serverQueryService;
        this.metricQueryService = metricQueryService;
    }

    @Operation(
            summary = "서버 목록 조회",
            description = "등록된 모니터링 대상 서버의 기본 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "서버 목록 조회 성공")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ServerSummaryResponse> getServers() {
        return serverQueryService.getServers();
    }

    @Operation(
            summary = "서버의 최신 지표 조회",
            description = "특정 서버가 마지막으로 전송한 CPU, 메모리, 디스크 지표를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "최신 지표 조회 성공"),
            @ApiResponse(responseCode = "404", description = "서버 또는 수집된 지표를 찾을 수 없음")
    })
    @GetMapping(
            value = "/{serverId}/metrics/latest",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public LatestMetricResponse getLatestMetric(
            @PathVariable Long serverId
    ) {
        return metricQueryService.getLatestMetric(serverId);
    }
}