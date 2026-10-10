package com.idps.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrafficIngestRequest {
    @NotBlank
    private String sourceIp;
    private double duration;
    @NotBlank private String protocolType;
    @NotBlank private String service;
    @NotBlank private String flag;
    private double srcBytes;
    private double dstBytes;
    private double count;
    private double srvCount;
    private double serrorRate;
    private double sameSrvRate;
    private double diffSrvRate;
    private double dstHostCount;
    private double dstHostSrvCount;
}
