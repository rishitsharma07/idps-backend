package com.idps.backend.repository;

import com.idps.backend.entity.TrafficRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrafficRecordRepository extends JpaRepository<TrafficRecord,Long> {

    List<TrafficRecord> findAllByOrderByCreatedAtDesc(Pageable pageable);

    long countAllByMalicious(boolean malicious);

}
