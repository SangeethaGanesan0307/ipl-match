package com.indium.spring_boot_assessment.service;

import com.indium.spring_boot_assessment.entites.MatchDetails;
import com.indium.spring_boot_assessment.repository.DeliveryDataRepository;
import com.indium.spring_boot_assessment.repository.MatchDetailsRepository;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

@Service
public class MatchDetailService {
    @Autowired
    private MatchDetailsRepository matchDetailsRepository;
    @Autowired
    private DeliveryDataRepository deliveryDataRepository;

    @Cacheable(value = "playername", key = "#playerName")
    public List<Object[]> getMatchesByPlayerName(String playerName) {
        return matchDetailsRepository.findMatchesByPlayerName(playerName);
    }
    @Cacheable(value = "score", key = "#playerName")
    public Long getCumulativeScoreByPlayerName(String playerName) {
        return deliveryDataRepository.getCumulativeScoreByPlayerName(playerName);
    }
    @Cacheable(value = "scorebydate", key = "#matchDate")
    public List<Integer> getScoresByDate(LocalDate matchDate) {
        return deliveryDataRepository.getScoresByDate(matchDate);
    }
    @Cacheable(value = "battername", key = "#pageable")
    public Page<Object[]> getTopBatsmen(Pageable pageable) {
        return deliveryDataRepository.findTopBatsmen(pageable);
    }
    @CacheEvict(value = {"playername","score","scorebydate","battername"},allEntries = true)
        public void clearCache() {
        }

    }


