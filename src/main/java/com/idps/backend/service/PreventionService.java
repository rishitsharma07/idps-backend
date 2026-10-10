package com.idps.backend.service;

import com.idps.backend.entity.BlockedIp;
import com.idps.backend.repository.BlockedIpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreventionService {

    private final BlockedIpRepository blockedIpRepository;

    public boolean isBlocked(String ip){
        return blockedIpRepository.findByIpAddressAndActiveTrue(ip).isPresent();
    }

    public void blockIp(String ip,String reason){
        if(isBlocked(ip)){
            return;
        }
        BlockedIp blocked = new BlockedIp();
        blocked.setIpAddress(ip);
        blocked.setReason(reason);
        blockedIpRepository.save(blocked);
    }

    public void unblockIp(String ip){
        blockedIpRepository.findByIpAddressAndActiveTrue(ip).ifPresent(blockedIp -> {
            blockedIp.setActive(false);
            blockedIpRepository.save(blockedIp);
        });
    }

    public List<BlockedIp> listActiveBlocks(){
        return blockedIpRepository.findAllByActiveTrueOrderByBlockedAtDesc();
    }

}
