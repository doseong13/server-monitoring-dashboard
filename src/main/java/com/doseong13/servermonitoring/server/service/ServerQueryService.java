package com.doseong13.servermonitoring.server.service;

import com.doseong13.servermonitoring.server.dto.ServerSummaryResponse;
import com.doseong13.servermonitoring.server.repository.ServerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ServerQueryService {

    private final ServerRepository serverRepository;

    public ServerQueryService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public List<ServerSummaryResponse> getServers() {
        return serverRepository.findAll()
                .stream()
                .map(server -> new ServerSummaryResponse(
                        server.getId(),
                        server.getName(),
                        server.getCpuThreshold(),
                        server.getLastSeenAt()
                ))
                .toList();
    }
}