package com.doseong13.servermonitoring.server.repository;

import com.doseong13.servermonitoring.server.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findByAgentKeyHash(String agentKeyHash);
}