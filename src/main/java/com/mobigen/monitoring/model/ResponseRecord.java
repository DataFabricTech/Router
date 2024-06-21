package com.mobigen.monitoring.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

public class ResponseRecord {
    @Builder
    public record ConnectionAvgResponse(UUID serviceID, BigDecimal avgResponseTime) {}

    @Builder
    public record ConnectStatusResponse(Long total, Long connected, Long disConnected) {}
}
