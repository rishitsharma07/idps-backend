package com.idps.backend.service;

import com.idps.backend.dto.ClassificationResponse;
import com.idps.backend.dto.TrafficIngestRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class MlClassificationService {

    private final RestClient restClient;

    public MlClassificationService(@Value("${idps.ml-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public ClassificationResponse classify(TrafficIngestRequest req) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry("source_ip", req.getSourceIp()),
                Map.entry("duration", req.getDuration()),
                Map.entry("protocol_type", req.getProtocolType()),
                Map.entry("service", req.getService()),
                Map.entry("flag", req.getFlag()),
                Map.entry("src_bytes", req.getSrcBytes()),
                Map.entry("dst_bytes", req.getDstBytes()),
                Map.entry("count", req.getCount()),
                Map.entry("srv_count", req.getSrvCount()),
                Map.entry("serror_rate", req.getSerrorRate()),
                Map.entry("same_srv_rate", req.getSameSrvRate()),
                Map.entry("diff_srv_rate", req.getDiffSrvRate()),
                Map.entry("dst_host_count", req.getDstHostCount()),
                Map.entry("dst_host_srv_count", req.getDstHostSrvCount())
        );

        return restClient.post()
                .uri("/classify")
                .body(body)
                .retrieve()
                .body(ClassificationResponse.class);
    }

}
