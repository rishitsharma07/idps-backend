package com.idps.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClassificationResponse {

    @JsonProperty("source_ip")
    private String sourceIp;

    private String prediction;

    @JsonProperty("is_malicious")
    private boolean malicious;

    private double confidence;
}
