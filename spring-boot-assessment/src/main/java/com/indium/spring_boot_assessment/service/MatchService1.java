//package com.indium.spring_boot_assessment.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.indium.spring_boot_assessment.entites.*;
//import com.indium.spring_boot_assessment.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class MatchService1 {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
////    @Autowired
////    private MatchRepository matchRepository;
//
//    @Autowired
//    private TeamRepository teamRepository;
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Autowired
//    private TeamPlayerRepository teamPlayerRepository;
//
//    @Autowired
//    private OfficialRepository officialRepository;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Transactional
//    public void saveMatchFromJson(String jsonData) throws IOException {
//        // Parse the JSON data using ObjectMapper
//        JsonNode rootNode = objectMapper.readTree(jsonData);
//
//        // Safely retrieve data and handle null values
//        String matchName  = safeGetText(rootNode, "name");
//        String matchDateStr = safeGetText(rootNode, "date");
//        String venue = safeGetText(rootNode, "venue");
//
//        //Create and save Match
//        Match match = new Match();
//        match.setName(matchName);
//        if (matchDateStr != null) {
//            match.setDate(LocalDate.parse(matchDateStr));
//        }
//        match.setVenue(venue);
//
//        // Extract and save teams from the "teams" array
//        JsonNode teamsNode = rootNode.get("info").get("teams");
//        if (teamsNode != null && teamsNode.isArray()) {
//            Team team1 = new Team();
//            team1.setName(teamsNode.get(0).asText());
//            teamRepository.save(team1);
//            match.setTeam1(team1);
//
//            Team team2 = new Team();
//            team2.setName(teamsNode.get(1).asText());
//            teamRepository.save(team2);
//            match.setTeam2(team2);
//        }
//
//        // Save the match to generate its ID
//        matchRepository.save(match);
//
//        // Save players, officials, events, etc. (add similar checks to ensure valid data)
//        // For players, officials, and other sections, you would process them similarly by accessing the correct nodes in the JSON.
//        // Ensure to check for nulls before calling .asText()
//
//        // Example for officials:
//        JsonNode officialsNode = rootNode.get("info").get("officials");
//        if (officialsNode != null) {
//            // Extract and save officials
//        }
//    }
//
//    // Helper method to safely retrieve text from nested JsonNode
//    private String safeGetText(JsonNode node, String... fieldNames) {
//        for (String field : fieldNames) {
//            node = node.get(field);
//            if (node == null || node.isNull()) {
//                return null;
//            }
//        }
//        return node.asText();
//    }
//
//    // Helper method to safely retrieve text from array node
//    private String safeGetText(JsonNode node, String field, int index) {
//        JsonNode arrayNode = node.get(field);
//        if (arrayNode != null && arrayNode.isArray() && arrayNode.has(index)) {
//            return arrayNode.get(index).asText();
//        }
//        return null;
//    }
//
//}
