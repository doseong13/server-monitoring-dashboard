package com.doseong13.servermonitoring.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI serverMonitoringOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Server Monitoring Dashboard API")
                        .description("VM 서버의 상태 지표를 수집하고 조회하는 API입니다.")
                        .version("v1"));
    }
}