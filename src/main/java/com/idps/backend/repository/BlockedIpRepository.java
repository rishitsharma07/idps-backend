package com.idps.backend.repository;

import com.idps.backend.entity.BlockedIp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockedIpRepository extends JpaRepository<BlockedIp, Long> {

    Optional<BlockedIp> findByIpAddressAndActiveTrue(String ipAddress);

    List<BlockedIp> findAllByActiveTrueOrderByBlockedAtDesc();
}
