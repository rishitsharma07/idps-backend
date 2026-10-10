package com.idps.backend.controller;

import com.idps.backend.dto.TrafficIngestRequest;
import com.idps.backend.entity.BlockedIp;
import com.idps.backend.entity.TrafficRecord;
import com.idps.backend.repository.TrafficRecordRepository;
import com.idps.backend.service.PreventionService;
import com.idps.backend.service.TrafficIngestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficIngestionService trafficIngestionService;
    private final TrafficRecordRepository trafficRecordRepository;
    private final PreventionService preventionService;

    @PostMapping("/traffic/ingest")
    public TrafficRecord ingest(@Valid @RequestBody TrafficIngestRequest request){
        return trafficIngestionService.ingest(request);
    }

    @GetMapping("/traffic/recent")
    public List<TrafficRecord> recent(@RequestParam(defaultValue = "50") int limit){
        return trafficRecordRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, limit));
    }

    @GetMapping("/blocked-ips")
    public List<BlockedIp> blockedIps(){
        return preventionService.listActiveBlocks();
    }
}
