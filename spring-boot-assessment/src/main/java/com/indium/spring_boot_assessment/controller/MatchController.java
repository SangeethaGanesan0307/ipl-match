package com.indium.spring_boot_assessment.controller;

import com.indium.spring_boot_assessment.service.IplService;
import com.indium.spring_boot_assessment.service.MatchDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ipl")
public class MatchController {

    @Autowired
    private IplService iplService;
    @Autowired
    private MatchDetailService matchDetailService;
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Operation(summary = "Insert match data from JSON", description = "Insert IPL match data using a JSON payload.")
    @ApiResponse(responseCode = "200", description = "Match data inserted successfully")
    @PostMapping("/insert")
    public ResponseEntity<String> insertMatchData(@RequestBody String jsonData) {
        try {
            iplService.insertMatchData(jsonData);
            matchDetailService.clearCache();
            kafkaTemplate.send("match-logs","Inserted");
            return ResponseEntity.ok("Match data inserted successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing JSON data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Operation(summary = "Get matches by player name", description = "Fetch all matches played by a given player.")
    @ApiResponse(responseCode = "200", description = "Matches fetched successfully")
    @GetMapping("/matches/player")
    public List<Object[]> getMatchesByPlayer(@RequestParam String playerName) {
        List<Object[]> player = matchDetailService.getMatchesByPlayerName(playerName);
        kafkaTemplate.send("match-logs","Query executed");
        return matchDetailService.getMatchesByPlayerName(playerName);
    }

    @Operation(summary = "Get cumulative score by player name", description = "Fetch the cumulative score of a player.")
    @ApiResponse(responseCode = "200", description = "Cumulative score fetched successfully")
    @GetMapping("/cumulative-score")
    public ResponseEntity<Long> getCumulativeScore(@RequestParam String fullName) {
        Long cumulativeScore = matchDetailService.getCumulativeScoreByPlayerName(fullName);
        kafkaTemplate.send("match-logs","Query executed");
        return ResponseEntity.ok(cumulativeScore);
    }

    @Operation(summary = "Get scores by match date", description = "Fetch scores by a specific match date.")
    @ApiResponse(responseCode = "200", description = "Scores fetched successfully")
    @GetMapping("/scores")
    public ResponseEntity<List<Integer>> getScoresByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate matchDate) {
        List<Integer> scores = matchDetailService.getScoresByDate(matchDate);
        kafkaTemplate.send("match-logs","Query executed");
        return ResponseEntity.ok(scores);
    }

    @Operation(summary = "Get top batsmen", description = "Fetch top batsmen in ascending order with pagination.")
    @ApiResponse(responseCode = "200", description = "Top batsmen fetched successfully")
    @GetMapping("/top-batsmen")
    public Page<Object[]> getTopBatsmen(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> paging = matchDetailService.getTopBatsmen(pageable);
        kafkaTemplate.send("match-logs","Query executed");
        return matchDetailService.getTopBatsmen(pageable);
    }
}




