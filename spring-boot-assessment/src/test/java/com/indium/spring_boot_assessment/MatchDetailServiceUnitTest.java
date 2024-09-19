package com.indium.spring_boot_assessment;

import com.indium.spring_boot_assessment.repository.DeliveryDataRepository;
import com.indium.spring_boot_assessment.repository.MatchDetailsRepository;
import com.indium.spring_boot_assessment.service.MatchDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class MatchDetailServiceUnitTest {

    @Mock
    private MatchDetailsRepository matchDetailsRepository;

    @Mock
    private DeliveryDataRepository deliveryDataRepository;

    @InjectMocks
    private MatchDetailService matchDetailService;

    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheManager = new ConcurrentMapCacheManager("playername", "score", "scorebydate", "battername");
    }

    @Test
    void testGetMatchesByPlayerName() {
        String playerName = "John Doe";
        List<Object[]> expectedMatches = Collections.singletonList(new Object[]{"Match1", "Details1"});
        when(matchDetailsRepository.findMatchesByPlayerName(playerName)).thenReturn(expectedMatches);

        List<Object[]> matches = matchDetailService.getMatchesByPlayerName(playerName);

        assertThat(matches).isEqualTo(expectedMatches);
        verify(matchDetailsRepository, times(1)).findMatchesByPlayerName(playerName);
    }

    @Test
    void testGetCumulativeScoreByPlayerName() {
        String playerName = "John Doe";
        Long expectedScore = 150L;
        when(deliveryDataRepository.getCumulativeScoreByPlayerName(playerName)).thenReturn(expectedScore);

        Long score = matchDetailService.getCumulativeScoreByPlayerName(playerName);

        assertThat(score).isEqualTo(expectedScore);
        verify(deliveryDataRepository, times(1)).getCumulativeScoreByPlayerName(playerName);
    }

    @Test
    void testGetScoresByDate() {
        LocalDate matchDate = LocalDate.of(2024, 9, 18);
        List<Integer> expectedScores = List.of(100, 200);
        when(deliveryDataRepository.getScoresByDate(matchDate)).thenReturn(expectedScores);

        List<Integer> scores = matchDetailService.getScoresByDate(matchDate);

        assertThat(scores).isEqualTo(expectedScores);
        verify(deliveryDataRepository, times(1)).getScoresByDate(matchDate);
    }

    @Test
    void testGetTopBatsmen() {
        Pageable pageable = Pageable.unpaged();
        Page<Object[]> expectedBatsmen = new PageImpl<>(Collections.singletonList(new Object[]{"Batsman1", "Score1"}));
        when(deliveryDataRepository.findTopBatsmen(pageable)).thenReturn(expectedBatsmen);

        Page<Object[]> batsmen = matchDetailService.getTopBatsmen(pageable);

        assertThat(batsmen).isEqualTo(expectedBatsmen);
        verify(deliveryDataRepository, times(1)).findTopBatsmen(pageable);
    }

    @Test
    void testClearCache() {
        // Simulate cache before clear
        cacheManager.getCache("playername").put("key", "value");
        cacheManager.getCache("score").put("key", "value");
        cacheManager.getCache("scorebydate").put("key", "value");
        cacheManager.getCache("battername").put("key", "value");

        // Test cache eviction
        matchDetailService.clearCache();
    }
}

