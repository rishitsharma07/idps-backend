package com.idps.backend.service;

import com.idps.backend.dto.ClassificationResponse;
import com.idps.backend.dto.TrafficIngestRequest;
import com.idps.backend.entity.TrafficRecord;
import com.idps.backend.repository.TrafficRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrafficIngestionService {

    private final MlClassificationService mlClassificationService;
    private final PreventionService preventionService;
    private final TrafficRecordRepository trafficRecordRepository;

    public TrafficRecord ingest(TrafficIngestRequest request){
        ClassificationResponse result = mlClassificationService.classify(request);

        TrafficRecord record = new TrafficRecord();
        record.setSourceIp(request.getSourceIp());
        record.setProtocolType(request.getProtocolType());
        record.setService(request.getService());
        record.setPrediction(result.getPrediction());
        record.setMalicious(result.isMalicious());
        record.setConfidence(result.getConfidence());

        if (result.isMalicious()){
            preventionService.blockIp(request.getSourceIp(), result.getPrediction());
            record.setBlockedAction(true);
        }

        return trafficRecordRepository.save(record);
    }
}
